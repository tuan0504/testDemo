package com.nntuan.insideout.listener

import android.accounts.NetworkErrorException
import io.reactivex.functions.Consumer
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Created by nntuan on 1/2/18.
 */
abstract class ErrorListener : Consumer<Throwable> {
    @Throws(Exception::class)
    override fun accept(e: Throwable) {
        var message: String? = null
        var response: Response<*>? = null

        try {

            if (e is SocketTimeoutException) {
                onMessage("Timeout to request!", "",null)
                return
            }
            if (e is ConnectException || e is NetworkErrorException || e is UnknownHostException) {
                onMessage("Network connection onError!", "",null)
                return
            }

        } catch (exp: Exception) {
            onMessage(e.localizedMessage, "", null)
        }

    }

    abstract fun onMessage(message: String?, field: String?, code: Int?)
}

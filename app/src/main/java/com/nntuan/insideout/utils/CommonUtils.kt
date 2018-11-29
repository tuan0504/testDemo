package com.nntuan.insideout.utils

import android.content.Context
import android.support.v7.app.AlertDialog
import android.util.Log
import com.blankj.utilcode.util.ToastUtils
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by nntuan on 1/2/18.
 */
class CommonUtils {

    companion object {

        const val USER_INFO = "user_info"
        const val MALE = "male"
        const val FEMALE = "female"
        const val VIETNAMESE = "vi"
        const val ENGLISH = "en-us"
        const val LANGUAGE = "language"
        const val VIBRATE = "vibrate"
        const val FORMAT_DATE_YYYY_MM_DD = "yyyy-MM-dd"
        const val FORMAT_DATE_MMMM_DD_YYYY_EN = "MMMM dd, yyyy"
        const val FORMAT_DATE_MMMM_DD_YYYY_VI = "dd MMMM yyyy"
        const val FORMAT_DATE_DEFAULT = "yyyy-MM-dd hh:mm:ss"
        const val FB_USERS = "users"
        const val PER_PAGE = 10

        var viewPagerPos = 1


        fun formattedDateFromString(inputFormat: String, outputFormat: String, inputDate: String?): String {
            var inputFormat = inputFormat
            var outputFormat = outputFormat
            if (inputFormat.isNullOrEmpty()) { // if inputFormat = "", set a default input format.
                inputFormat = FORMAT_DATE_DEFAULT
            }
            if (outputFormat.isNullOrEmpty()) {
                outputFormat = FORMAT_DATE_DEFAULT // if inputFormat = "", set a default output format.
            }
            var parsed: Date? = null
            var outputDate = ""

            val dfInput = SimpleDateFormat(inputFormat, Locale.getDefault())
            val dfOutput = SimpleDateFormat(outputFormat, Locale.getDefault())

            try {
                parsed = dfInput.parse(inputDate ?: "")
                outputDate = dfOutput.format(parsed)
            } catch (e: Exception) {
                Log.e("formattedDateFromString", "Exception in formateDateFromstring(): " + e.message)
            }

            return outputDate

        }

        fun showMessage(context: Context, message: String?, code: Int?) {
            val simpleAlert = AlertDialog.Builder(context).create()
            when (code) {
                401 -> ToastUtils.showShort(message)
                else -> {
                    simpleAlert.setMessage(message ?: "Error")
                    simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, "OK", { dialogInterface, i ->
                        simpleAlert.dismiss()
                    })
                    simpleAlert.show()
                }
            }
        }
    }


    enum class Status(var status: String) {
        ACTIVE("active"),
        WAITING_BILL("waiting_bill"),
        PAID("paid"),
        AUTH("auth"),
        AUTH_FAILED("auth_failed"),
        UNDEFINED("undefined")
    }

    enum class Position(var pos: Int) {
        ZERO(0),
        FIRST(1)
    }
}
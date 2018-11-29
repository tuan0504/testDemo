package com.nntuan.insideout.service

import android.util.Log
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.nntuan.insideout.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by nntuan on 12/28/17.
 */


class ApiHelper {
    companion object {


        private var instance: ApiHelper? = null

        fun newInstance(): ApiHelper {
            if (instance == null) {
                instance = ApiHelper()
            }
            return instance as ApiHelper
        }
    }

    private fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(LoggingInterceptor.Builder()
                        .loggable(BuildConfig.DEBUG)
                        .setLevel(Level.BODY)
                        .log(Log.DEBUG)
                        .request("Request")
                        .response("Response")
                        .build())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(15 * 60, TimeUnit.SECONDS)
                .build()
    }


    fun getApiService(): ApiService {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient())
                .build()
        return retrofit.create(ApiService::class.java)
    }
}


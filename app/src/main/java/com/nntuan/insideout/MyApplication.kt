package com.nntuan.insideout

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.nntuan.insideout.model.UserInfo

/**
 * Created by nntuan on 1/3/18.
 */
class MyApplication : Application() {

    companion object {
        lateinit var instance: MyApplication
            private set

        lateinit var userDetail: UserInfo
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Utils.init(this)
    }

}
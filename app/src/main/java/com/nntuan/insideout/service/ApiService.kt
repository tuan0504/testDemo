package com.nntuan.insideout.service

import com.nntuan.insideout.model.UserInfo
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by nntuan on 12/28/17.
 */
interface ApiService {

    companion object {
        const val LOGIN = "LOGIN"
        const val USERS = "/users"
    }

    @GET( USERS + "/{Username}")
    fun getUserInfo(@Path("Username") username : String): Flowable<UserInfo>

    @GET( USERS)
    fun getListUserInfo(): Flowable<List<UserInfo>>

}
package com.nntuan.insideout.model

import com.google.gson.annotations.SerializedName

/**
 * Created by nntuan on 1/2/18.
 */
class UserInfo {
    //shortcut info
    @SerializedName("id")
    var id: Int? = 0
    @SerializedName("login")
    var login: String = "login"
    @SerializedName("avatar_url")
    var avatarUrl: String? = "https://homepages.cae.wisc.edu/~ece533/images/cat.png"
    @SerializedName("site_admin")
    var staff: Boolean = false

    //full info
    @SerializedName("name")
    var name: String? = null
    @SerializedName("blog")
    var blog: String? = null
    @SerializedName("location")
    var location: String? = null
    @SerializedName("bio")
    var bio: String? = null
}
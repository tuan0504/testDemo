package com.nntuan.insideout.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nntuan.insideout.MyApplication
import com.nntuan.insideout.R
import com.nntuan.insideout.model.UserInfo
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel)
        toolbar.setNavigationOnClickListener(View.OnClickListener {
            finish();
        })

        MyApplication.userDetail?.let {
            updateUIWithUser(it)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun updateUIWithUser(user: UserInfo) {

        Glide.with(baseContext)
                .load(user.avatarUrl)
                .apply(RequestOptions.placeholderOf(R.drawable.placeholder))
                .into(imvUser)

        tvUserName.text = user.name
        tvBio.text = user.bio

        tvUserLogin.text = user.login;
        if(user.staff) {
            tvSiteAdmin.visibility = View.VISIBLE
        } else {
            tvSiteAdmin.visibility = View.GONE
        }

        tvLocation.text = user.location
        tvBlog.text = user.blog
    }
}

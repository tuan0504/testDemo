package com.nntuan.insideout.ui.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.kaopiz.kprogresshud.KProgressHUD
import com.nntuan.insideout.MyApplication
import com.nntuan.insideout.R
import com.nntuan.insideout.adapter.UserInfosAdapter
import com.nntuan.insideout.listener.ErrorListener
import com.nntuan.insideout.model.UserInfo
import com.nntuan.insideout.service.ApiHelper
import com.nntuan.insideout.service.ApiService
import com.nntuan.insideout.utils.CommonUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), (UserInfo) -> Unit {

    private var layoutManager: LinearLayoutManager? = null
    private var adapter: UserInfosAdapter? = null
    private var compositeDisposable: CompositeDisposable? = null
    private var progress: KProgressHUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        compositeDisposable = CompositeDisposable()
        layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        rvListUsers.layoutManager = layoutManager
        adapter = UserInfosAdapter(this)
        rvListUsers.adapter = adapter

        progress = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel(resources.getString(R.string.please_wait))
                .setCancellable(false)
        progress?.show();

        compositeDisposable?.add(ApiHelper.newInstance().getApiService().getListUserInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { response ->
                    progress?.dismiss()
                    response?.let {
                        adapter?.setData(ArrayList(it))
                    }
                }, object : ErrorListener() {
                    override fun onMessage(message: String?, field: String?, code: Int?) {
                        progress?.dismiss()
                        CommonUtils.showMessage(this@HomeActivity, message, code)
                    }
                }))
    }

    override fun invoke(user: UserInfo) {
        var userLogin : String = user.login

        compositeDisposable?.add(ApiHelper.newInstance().getApiService().getUserInfo(userLogin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer { response ->
                    progress?.dismiss()
                    response?.let {
                        MyApplication.userDetail = it
                        val intent = Intent(this, ProfileActivity::class.java)
                        startActivity(intent)
                    }
                }, object : ErrorListener() {
                    override fun onMessage(message: String?, field: String?, code: Int?) {
                        progress?.dismiss()
                        CommonUtils.showMessage(this@HomeActivity, message, code)
                    }
                }))

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    override fun onDestroy() {
        super.onDestroy()
        progress?.dismiss()
        compositeDisposable?.dispose()
    }
}

package com.nntuan.insideout.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.nntuan.insideout.R
import com.nntuan.insideout.model.UserInfo
import kotlinx.android.synthetic.main.item_row_user.view.*


/**
 * Created by nntuan on 1/17/18.
 */
class UserInfosAdapter(private var onClickItemUser: (UserInfo) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return UsersViewHolder(parent!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        (holder as UsersViewHolder).bind(listUser, onClickItemUser)
    }

    private var listUser: ArrayList<UserInfo>? = null
    override fun getItemCount() = listUser?.size?: 0

    fun setData(listUsers: ArrayList<UserInfo>?) {
        this.listUser = listUsers
        notifyDataSetChanged()
    }

    class UsersViewHolder(itemView: ViewGroup) :
            RecyclerView.ViewHolder(LayoutInflater.from(itemView.context).inflate(R.layout.item_row_user, itemView, false)) {

        fun bind(listUsers: ArrayList<UserInfo>?, onClickItemUser: (UserInfo) -> Unit) {
            listUsers?.get(adapterPosition)?.let {
                var info: UserInfo = it;

                itemView.setOnClickListener { onClickItemUser.invoke(info) }
                itemView.tvUserLogin.text = it.login;

                if(it.staff) {
                    itemView.tvSiteAdmin.visibility = View.VISIBLE
                } else {
                    itemView.tvSiteAdmin.visibility = View.GONE
                }

                Glide.with(itemView.context)
                        .load(it.avatarUrl)
                        .apply(RequestOptions.placeholderOf(R.drawable.placeholder))
                        .into(itemView.imvUser)
            }
        }
    }
}



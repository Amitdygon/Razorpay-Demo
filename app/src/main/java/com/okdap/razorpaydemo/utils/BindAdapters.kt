package com.okdap.razorpaydemo.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter("setImageUrl")
fun setImageUrl(view:ImageView,url:String?){
    Glide.with(view.context).load(url).into(view)
}
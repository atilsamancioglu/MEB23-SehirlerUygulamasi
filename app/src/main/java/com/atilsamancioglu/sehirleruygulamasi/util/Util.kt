package com.atilsamancioglu.sehirleruygulamasi.util

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.atilsamancioglu.sehirleruygulamasi.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

//Extension
/*
fun String.benimExtension(parametre : String) {
    println(parametre)
}

*/

fun ImageView.downloadGorsel(url:String?, progressDrawable: CircularProgressDrawable){

    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun placeholderProgressBar(context : Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}

@BindingAdapter("android:downloadGorsel")
fun downloadGorsel(view: ImageView, url:String?) {
    view.downloadGorsel(url, placeholderProgressBar(view.context))
}
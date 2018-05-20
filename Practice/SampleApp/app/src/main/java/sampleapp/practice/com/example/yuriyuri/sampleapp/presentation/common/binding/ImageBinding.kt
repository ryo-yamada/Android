package sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.common.binding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun imageUrl(imageView: ImageView, url: String?) {
    url ?: return
    Glide.with(imageView.context).load(url).into(imageView)
}

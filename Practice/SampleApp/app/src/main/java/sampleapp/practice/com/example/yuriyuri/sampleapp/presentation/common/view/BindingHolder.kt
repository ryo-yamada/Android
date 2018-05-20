package sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.common.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.content.Context
import android.view.ViewGroup
import android.support.annotation.LayoutRes

class BindingHolder<out T : ViewDataBinding>
(context: Context,
 parent: ViewGroup,
 @LayoutRes layoutResId: Int)
    : RecyclerView.ViewHolder(LayoutInflater.from(context).inflate(layoutResId, parent, false)) {
    val binding: T? = DataBindingUtil.bind(itemView)
}

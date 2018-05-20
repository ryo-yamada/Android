package sampleapp.practice.com.example.yuriyuri.sampleapp.presentation.common.binding

import android.databinding.BindingAdapter
import android.widget.TextView
import sampleapp.practice.com.example.yuriyuri.model.TagId

/**
 * TagIdをTextViewにバインドするためのカスタムAdapter
 */
@BindingAdapter(value = ["android:text"])
fun TextView.setTagName(tagId: TagId) {
    text = tagId.value
}
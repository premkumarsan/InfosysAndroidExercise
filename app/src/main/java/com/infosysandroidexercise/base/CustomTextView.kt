package com.infosysandroidexercise.base

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


class CustomTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    init {
        val tf = Typeface.createFromAsset(getContext().assets, "fonts/proxima_nova_medium.ttf")
        typeface = tf
    }
}
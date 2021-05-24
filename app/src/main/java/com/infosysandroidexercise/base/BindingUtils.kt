package com.infosysandroidexercise.base

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.infosysandroidexercise.R
import com.infosysandroidexercise.retrofit.SeparatorDecoration
import com.squareup.picasso.Picasso


object BindingUtils {

    @BindingAdapter(value = ["setAdapter"])
    @JvmStatic
    fun RecyclerView.bindRecyclerViewAdapter(adapter: RecyclerView.Adapter<*>) {
        this.run {
            this.setHasFixedSize(true)
            val height = resources.getDimension(R.dimen._1sdp)
            this.addItemDecoration(
                SeparatorDecoration(
                    this.context,
                    Color.parseColor("#ebeff0"),
                    height
                )
            )
            this.itemAnimator = DefaultItemAnimator()
            this.adapter = adapter
        }
    }


    @BindingAdapter("setPadding")
    @JvmStatic
    fun setPadding(view: View, padding: Float) {
        view.setPadding(
            padding.toInt(),
            padding.toInt(),
            padding.toInt(),
            padding.toInt()
        )
    }

    /*
     * Loading the image to Picasso
     */
    @BindingAdapter("loadImage", "picasso")
    @JvmStatic
    fun setImageViewResource(imageView: ImageView, url: String?, picasso: Picasso) {
        if (url != null && url.isNotEmpty() && !url.equals("null", ignoreCase = true)) {
            picasso.load(url.replace("http", "https")).placeholder(R.drawable.no_img_placeholder)
                .error(R.drawable.no_img_placeholder).into(imageView)
        }
    }


    /*
     * BindingAdapter to set the data to the textView if data is not empty
     */
    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("setText")
    fun setTextView(textView: TextView, data: String?) {
        if (data == null || data.trim().isEmpty()) {
            textView.text = textView.context.resources.getString(R.string.nil)
        } else {
            textView.text = data
        }
    }

}
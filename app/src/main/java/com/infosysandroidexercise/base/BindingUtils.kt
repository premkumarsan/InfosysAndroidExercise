package com.infosysandroidexercise.base

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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
    fun setPadding(view: View, padding: Int) {
        view.setPadding(
            padding,
            padding,
            padding,
            padding
        )
    }


    @BindingAdapter("setPaddingStart")
    @JvmStatic
    fun setPaddingStart(view: View, padding: Int) {
        view.setPadding(
            padding,
            view.paddingTop,
            view.paddingRight,
            view.paddingBottom
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


    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            view.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


    @BindingAdapter("hideKeyboardOnInputDone")
    @JvmStatic
    fun hideKeyboardOnInputDone(view: EditText, enabled: Boolean) {
        if (!enabled) return
        val listener = TextView.OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                view.clearFocus()
                val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE)
                        as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
            false
        }
        view.setOnEditorActionListener(listener)
    }

}
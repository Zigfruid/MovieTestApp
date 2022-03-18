package com.example.testmovieapp.core.extentions

import android.content.Context
import android.content.res.Resources
import android.text.SpannableStringBuilder
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.example.testmovieapp.BuildConfig

fun View.visibility(visibility: Boolean): View {
    if (visibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
    return this
}

fun View.enabled(isEnabled: Boolean): View {
    this.isEnabled = isEnabled
    return this
}
val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

fun Fragment.showMessage(msg: String?) {
    Toast.makeText(this.requireContext(), msg, Toast.LENGTH_LONG).show()
}

fun ViewGroup.inflate(@LayoutRes id: Int): View =
    LayoutInflater.from(context).inflate(id, this, false)

fun RecyclerView.addVertDivider(context: Context?) {
    this.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
}

fun RecyclerView.addHorizDivider(context: Context?) {
    this.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
}

fun View.onClick(onClick: (View) -> Unit) {
    this.setOnClickListener(onClick)
}



private fun makeLinkClickable(
    strBuilder: SpannableStringBuilder,
    span: URLSpan,
    onLinkClick: (link: String) -> Unit
) {
    val start = strBuilder.getSpanStart(span)
    val end = strBuilder.getSpanEnd(span)
    val flags = strBuilder.getSpanFlags(span)
    val clickable = object : ClickableSpan() {
        override fun onClick(widget: View) {
            val fullText = (widget as TextView).text.toString()
            val link = fullText.substring(start, end)
            onLinkClick.invoke(link)
        }
    }
    strBuilder.setSpan(clickable, start, end, flags)
    strBuilder.removeSpan(span)
}

fun <T : ViewBinding> T.scope(block: T.() -> Unit) {
    block(this)
}


fun OkHttpClient.Builder.addLoggingInterceptor(context:Context): OkHttpClient.Builder {
    HttpLoggingInterceptor.Level.HEADERS
    val logging = HttpLoggingInterceptor.Logger { message -> Log.d("HTTP",message) }
    if (BuildConfig.LOGGING) {
        addInterceptor(HttpLoggingInterceptor(logging))
    }
    return this
}



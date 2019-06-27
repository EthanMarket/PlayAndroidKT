package com.ethan.playandroidkt.web

import android.view.ViewGroup
import com.ethan.core.api.base.BaseWebActivity
import com.ethan.playandroidkt.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseWebActivity() {
    override fun setContentViewId(): Int = R.layout.activity_web
    override fun getWebLayout(): ViewGroup = mWebLayout
    override fun getUrl(): String = intent.getStringExtra("link")
}

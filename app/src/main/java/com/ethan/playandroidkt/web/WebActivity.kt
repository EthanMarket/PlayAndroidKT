package com.ethan.playandroidkt.web

import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ethan.core.api.base.BaseWebActivity
import com.ethan.playandroidkt.R
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : BaseWebActivity() {
    private lateinit var mToolBarTv: TextView
    private lateinit var mToolBackIv: ImageView
    override fun setContentViewId(): Int = R.layout.activity_web
    override fun getWebLayout(): ViewGroup = mWebLayout
    override fun getUrl(): String = intent.getStringExtra("link")
    override fun processLogic() {
        super.processLogic()
        mToolBarTv = findViewById(R.id.mToolBarTv)
        mToolBackIv = findViewById(R.id.mToolBackIv)
        mToolBackIv.setOnClickListener {
            finish()
        }
        mToolBarTv.text=intent.getStringExtra("title")
    }
}

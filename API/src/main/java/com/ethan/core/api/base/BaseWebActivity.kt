package com.ethan.core.api.base

import android.view.KeyEvent
import android.view.ViewGroup
import android.widget.LinearLayout
import com.ethan.core.api.R
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient

abstract class BaseWebActivity : AbsBaseActivity() {
    protected lateinit var mAgentWeb: AgentWeb
    abstract fun getWebLayout(): ViewGroup
    abstract fun getUrl(): String
    override fun initDecorView() {
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(getWebLayout(), LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
//            .setWebChromeClient(mWebChromeClient)
//            .setWebViewClient(mWebViewClient)
            .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
//            .setWebLayout(WebLayout(this))
            .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
            .interceptUnkownUrl() //拦截找不到相关页面的Scheme
            .createAgentWeb()
            .ready().go(getUrl());
    }



/*******************AgentWeb生命周期   start****************************/
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause();
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb.webLifeCycle.onDestroy()
        super.onDestroy()
    }
/*************************AgentWeb生命周期   end******************************/
}
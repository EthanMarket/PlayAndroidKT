package com.ethan.playandroidkt.category

import android.os.Bundle
import android.view.ViewGroup
import com.ethan.core.api.base.BaseNetFragment

class CategoryFragment : BaseNetFragment() {

    private lateinit var mCid: String
    override fun requestDataStart() {
    }

    override fun initView(rootView: ViewGroup?) {
        super.initView(rootView)
        mCid = arguments!!.getString("cid")
    }
    override fun initWidget(savedInstanceState: Bundle?) {

    }

}


object CategoryFactory {
    fun createCategoryFragment(cid: String): CategoryFragment {
        return CategoryFragment().apply {
            arguments = Bundle().apply {
                putString("cid", cid);
            }
        }
    }
}
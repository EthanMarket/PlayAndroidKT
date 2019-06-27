package com.ethan.playandroidkt.category

import com.ethan.core.api.base.AbsBaseActivity
import com.ethan.core.api.entity.TreeBean
import com.ethan.playandroidkt.R
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AbsBaseActivity() {
    override fun setContentViewId(): Int = R.layout.activity_category
    private lateinit var mTreeBean: TreeBean
    private val mFragmentList = mutableListOf<CategoryFragment>()
    override fun initDecorView() {
        mTreeBean = intent.getSerializableExtra("TreeBean") as TreeBean
        mTreeBean.children.apply {
            forEach {
                mFragmentList.add(CategoryFactory.createCategoryFragment(it.id))
            }
        }
        mCategoryViewpager.adapter = TabCategoryPageAdapter(supportFragmentManager, mFragmentList, mTreeBean.children)
        mCategoryTabLayout.setupWithViewPager(mCategoryViewpager)
    }
}

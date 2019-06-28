package com.ethan.playandroidkt.category

import android.widget.ImageView
import android.widget.TextView
import com.ethan.core.api.base.AbsBaseActivity
import com.ethan.core.api.entity.TreeBean
import com.ethan.playandroidkt.R
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AbsBaseActivity() {
    override fun setContentViewId(): Int = R.layout.activity_category
    private lateinit var mTreeBean: TreeBean
    private lateinit var mToolBarTv: TextView
    private lateinit var mToolBackIv: ImageView
    private val mFragmentList = mutableListOf<CategoryFragment>()
    override fun initDecorView() {
        mToolBarTv = findViewById(R.id.mToolBarTv)
        mToolBackIv = findViewById(R.id.mToolBackIv)
        mToolBackIv.setOnClickListener {
            finish()
        }
        mTreeBean = intent.getSerializableExtra("TreeBean") as TreeBean
        mToolBarTv.text = mTreeBean.name
        mTreeBean.children.apply {
            forEach {
                mFragmentList.add(CategoryFactory.createCategoryFragment(it.id))
            }
        }
        mCategoryViewpager.adapter = TabCategoryPageAdapter(supportFragmentManager, mFragmentList, mTreeBean.children)
        mCategoryTabLayout.setupWithViewPager(mCategoryViewpager)
    }
}

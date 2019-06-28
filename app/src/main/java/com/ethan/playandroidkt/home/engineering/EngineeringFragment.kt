package com.ethan.playandroidkt.home.engineering

import android.content.Intent
import android.os.Bundle
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.entity.EngineeringBean
import com.ethan.playandroidkt.R
import com.ethan.playandroidkt.home.HomeAbsFragment
import com.ethan.playandroidkt.project.ProjectActivity

class EngineeringFragment : HomeAbsFragment(), CommonContract.IView<List<EngineeringBean>> {
    private lateinit var mAdapter: EngineeringAdapter
    private var mEngineeringList = mutableListOf<EngineeringBean>()
    private val mPresenter: EngineeringPresenter by lazy { EngineeringPresenter() }
    override fun responseEntrySuccess(isRefresh: Boolean, entry: List<EngineeringBean>) {
        mSwipeRefreshLayout.isRefreshing = false
        if (isRefresh) {
            mEngineeringList.clear()
        }
        mEngineeringList.apply {
            addAll(entry)
            mMultipleStatusView.apply {
                if (size == 0) showEmpty() else showContent()
            }
        }
        mAdapter.notifyDataSetChanged()
    }

    override fun initWidget(savedInstanceState: Bundle?) {
        mPresenter.apply {
            attachView(this@EngineeringFragment)
            requestEntryData(true)
        }
        mAdapter = EngineeringAdapter(R.layout.item_engineering_fragment, mEngineeringList)
        mAdapter.apply {
            setOnItemClickListener { _, _, position ->
//                showToast(mEngineeringList[position].toString())
                startActivity(Intent(activity,ProjectActivity::class.java).apply {
                    putExtra("name",mEngineeringList[position].name)
                    putExtra("cid",mEngineeringList[position].id)
                    println("=====id=======>"+mEngineeringList[position].id)
                    println("=====id=======>"+mEngineeringList[position].toString())
                })
            }
            mRecyclerView.adapter = this
        }
    }

    override fun requestDataStart() {
        mPresenter.apply {
            requestEntryData(true)
            mSwipeRefreshLayout.setOnRefreshListener {
                requestEntryData(true)
            }
        }
    }
}
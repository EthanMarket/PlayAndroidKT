package com.ethan.playandroidkt.search

import android.content.Intent
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.ethan.core.api.base.AbsBaseActivity
import com.ethan.core.api.base.CommonContract
import com.ethan.core.api.db.DbManager
import com.ethan.core.api.db.entity.SearchHistoryEntity
import com.ethan.core.api.entity.HotkeyBean
import com.ethan.playandroidkt.R
import com.ethan.playandroidkt.project.ProjectActivity
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import kotlinx.android.synthetic.main.activity_search.*
import java.text.SimpleDateFormat


class SearchActivity : AbsBaseActivity(), CommonContract.IView<List<HotkeyBean>> {
    private val mDataList = mutableListOf<HotkeyBean>()
    private var historyList = mutableListOf<SearchHistoryEntity>()
    private lateinit var mFlowLayout: TagFlowLayout
    private val mPresenter by lazy { SearchPresenter() }
    override fun setContentViewId(): Int = R.layout.activity_search
    override fun initDecorView() {
        showLoadingView()

        mFlowLayout = findViewById<TagFlowLayout>(R.id.search_flowlayout)
        mToolBackIv.setOnClickListener { finish() }
        mPresenter.apply {
            attachView(this@SearchActivity)
            requestEntryData(false)
        }
        mSearchTv?.setOnKeyListener(mOnKeyListener)

        DbManager.instance.getDbHelper().getSearchHistoryDao().selectSearchHistoryList().apply {
            when {
                size == 0 -> mSearchHistoryLayout.visibility = View.GONE
                size < 5 -> historyList.addAll(this)
                else -> {
                    historyList.addAll(take(5))
                }
            }
        }
        if (historyList.size > 0) {
            mSearchHistoryLayout.visibility = View.VISIBLE
            mHistoryRecyclerView.apply {
                layoutManager = LinearLayoutManager(this@SearchActivity)
                adapter = HistoryAdapter(R.layout.item_search_history, historyList)
                addItemDecoration(
                    DividerItemDecoration(
                        this@SearchActivity,
                        DividerItemDecoration.VERTICAL
                    )
                );
            }
        }
        mSearchClearHistoryTv.setOnClickListener {
            DbManager.instance.getDbHelper().getSearchHistoryDao().deleteSearchHistory()
            mSearchHistoryLayout.visibility = View.GONE
        }
        mSearchCancelIv.setOnClickListener {
            mSearchTv.setText("")
            mSearchCancelIv.visibility = View.GONE
        }
        mSearchTv.addTextChangedListener(mTextWatcher)
        mSearchHistoryLayout.setOnClickListener { mSearchHistoryLayout.visibility = View.GONE }
    }

    private val mOnKeyListener = View.OnKeyListener { _, keyCode, event ->

        if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
            var searchContent: String = mSearchTv.text.toString().trim()
            val currentTime = System.currentTimeMillis()
            val timeNow = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(currentTime)
            val searchHistoryDao = DbManager.instance.getDbHelper().getSearchHistoryDao()
            if (historyList.size < 5) {
                searchHistoryDao.insertSearchHistory(SearchHistoryEntity(searchContent, timeNow))
            } else {
                var entry = historyList.last()
                entry.historyName = searchContent
                entry.historyTime = timeNow
                searchHistoryDao.update(entry)
            }

            startActivity(Intent(this@SearchActivity, ProjectActivity::class.java).apply {
                putExtra("keyword", searchContent)
                putExtra("name", searchContent)
            })
            return@OnKeyListener true;
        }
        return@OnKeyListener false;
    }

    override fun responseEntrySuccess(isRefresh: Boolean, entry: List<HotkeyBean>) {
        dismissLoadingView()
        mDataList.addAll(entry)
        mFlowLayout.adapter = object : TagAdapter<HotkeyBean>(entry) {
            override fun getView(parent: FlowLayout?, position: Int, bean: HotkeyBean?): View {
                var mInflater = LayoutInflater.from(this@SearchActivity)
                val tv = mInflater.inflate(
                    R.layout.item_tablayout,
                    mFlowLayout, false
                ) as TextView
                tv.text = bean?.name
                return tv
            }
        }

        mFlowLayout.setOnTagClickListener { _, position, _ ->
            startActivity(Intent(this@SearchActivity, ProjectActivity::class.java).apply {
                putExtra("keyword", mDataList[position].name)
                putExtra("name", mDataList[position].name)
            })
            return@setOnTagClickListener true
        }
    }


    override fun showError(errorMsg: String) {
        dismissLoadingView()
    }

    private val mTextWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            mSearchCancelIv.visibility = View.VISIBLE
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

        }
    }
}


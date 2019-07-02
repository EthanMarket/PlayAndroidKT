package com.ethan.playandroidkt.search

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ethan.core.api.db.entity.SearchHistoryEntity
import com.ethan.playandroidkt.R

class HistoryAdapter(layoutResId: Int, data: List<SearchHistoryEntity>?) :
    BaseQuickAdapter<SearchHistoryEntity, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: SearchHistoryEntity?) {

        helper?.apply {
            item?.apply {
                setText(R.id.item_search_history_tv, historyName)
                setText(R.id.item_search_history_time_tv, historyTime)
            }
        }
    }
}
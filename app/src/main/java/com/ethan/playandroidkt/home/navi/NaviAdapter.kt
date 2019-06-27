package com.ethan.playandroidkt.home.navi

import com.chad.library.adapter.base.BaseSectionQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ethan.playandroidkt.R

class NaviAdapter(layoutResId: Int, sectionHeadResId: Int, data: MutableList<NaviSection>) :
    BaseSectionQuickAdapter<NaviSection, BaseViewHolder>(layoutResId, sectionHeadResId, data) {
    override fun convertHead(helper: BaseViewHolder?, item: NaviSection?) {
        helper?.setText(R.id.navi_header_tv, item?.header)
    }

    override fun convert(helper: BaseViewHolder?, item: NaviSection?) {
        helper?.setText(R.id.navi_second_tv, item?.t?.title)
    }
}
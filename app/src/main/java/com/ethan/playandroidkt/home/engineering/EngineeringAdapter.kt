package com.ethan.playandroidkt.home.engineering

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ethan.core.api.entity.EngineeringBean
import com.ethan.playandroidkt.R

class EngineeringAdapter(layoutResId: Int, data: MutableList<EngineeringBean>) :
    BaseQuickAdapter<EngineeringBean, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: EngineeringBean?) {
        helper?.apply {
            item?.apply {
                setText(R.id.engineering_name_tv, name)
            }
        }
    }
}
package com.ethan.playandroidkt.home.tree

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ethan.core.api.entity.TreeBean
import com.ethan.playandroidkt.R

class TreeAdapter(layoutResId: Int, data: MutableList<TreeBean>) :
    BaseQuickAdapter<TreeBean, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder, item: TreeBean) {
        helper?.apply {
            item?.apply {
                setText(R.id.tree_name_tv, name)
                setText(
                    R.id.tree_child_name_tv,
                    children.joinToString("     ", transform = { child ->
                        child.name
                    })
                )
            }
        }
    }
}
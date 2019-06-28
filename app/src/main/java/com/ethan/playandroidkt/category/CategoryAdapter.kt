package com.ethan.playandroidkt.category

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ethan.core.api.entity.Data
import com.ethan.playandroidkt.R

class CategoryAdapter(layoutResId: Int, data: MutableList<Data>?) :
    BaseQuickAdapter<Data, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: Data?) {
        helper?.apply {
            item?.apply {
                setText(R.id.category_user_name_tv, author)
                setText(R.id.category_user_time_tv, niceDate)
                setText(R.id.article_content_tv, title)
                setImageResource(
                    R.id.article_collect_iv,
                    if (collect) R.drawable.article_collect_like_icon else R.drawable.article_collect_unlike_icon
                )
            }
        }

    }

}
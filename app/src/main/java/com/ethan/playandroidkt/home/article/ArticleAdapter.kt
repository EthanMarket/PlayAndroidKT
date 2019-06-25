package com.ethan.playandroidkt.home.article

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ethan.core.api.entity.DataX
import com.ethan.playandroidkt.R

class ArticleAdapter(layoutResId: Int, data: MutableList<DataX>?) :
    BaseQuickAdapter<DataX, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: DataX?) {
        item?.apply {
            helper?.apply {
                setText(R.id.article_user_name_tv, author)
                setText(R.id.article_user_time_tv, niceDate)
                setText(R.id.article_content_tv, title)
                setText(R.id.article_classify_tv,chapterName)
                setText(R.id.article_superChapterName_tv,superChapterName)
                setImageResource(
                    R.id.article_collect_iv,
                    if (collect) R.drawable.article_collect_like_icon else R.drawable.article_collect_unlike_icon
                )
            }
        }
    }
}
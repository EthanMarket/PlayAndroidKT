package com.ethan.playandroidkt.project

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ethan.core.api.entity.ProjectDetails
import com.ethan.playandroidkt.R
import com.bumptech.glide.Glide


class ProjectAdapter(layoutResId: Int, data: MutableList<ProjectDetails>?) :
    BaseQuickAdapter<ProjectDetails, BaseViewHolder>(layoutResId, data) {
    override fun convert(helper: BaseViewHolder?, item: ProjectDetails?) {
        helper?.apply {
            item?.apply {
                setText(R.id.item_project_title_tv, title)
                setText(R.id.item_project_desc_tv, desc)
                setText(R.id.item_project_author_tv, author)
                setText(R.id.item_project_time_tv, niceDate)
                Glide.with(mContext).load(envelopePic).into(helper.getView(R.id.item_project_iv) as ImageView)
            }
        }
    }
}
package com.ethan.playandroidkt.home.navi

import com.chad.library.adapter.base.entity.SectionEntity
import com.ethan.core.api.entity.Article

class NaviSection(
    isHeader: Boolean = false,
    header: String? = null,
    article: Article? = null
) : SectionEntity<Article>(
    isHeader, header
) {
    init {
        t = article
    }
}
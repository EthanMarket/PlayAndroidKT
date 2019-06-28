package com.ethan.core.api.entity

data class EngineeringBean(
    val children: List<Any>,
    val courseId: Int,
    val id: String,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)
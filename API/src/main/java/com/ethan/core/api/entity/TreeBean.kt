package com.ethan.core.api.entity

import java.io.Serializable


data class TreeBean(
    val children: List<Children>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
) : Serializable

data class Children(
    val children: List<Any>,
    val courseId: Int,
    val id: String,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
) : Serializable
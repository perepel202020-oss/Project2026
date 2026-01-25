package com.perepel.data.local.entity

data class RuleEntity(
    val id: String,
    val title: String,
    val description: String,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val order: Int = 0
)
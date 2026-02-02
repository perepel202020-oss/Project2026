package com.perepel.domain.model

data class Rule(
    val id: String,
    val title: String,
    val description: String,
    val isActive: Boolean = true,
    val createdAt: Long = System.currentTimeMillis(),
    val order: Int = 0
)
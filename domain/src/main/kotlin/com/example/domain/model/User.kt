package com.example.domain.model

data class User(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val is_checked: Boolean?,
)

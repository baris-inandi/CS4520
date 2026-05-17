package com.itsbaris.cs4520.a4.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val name: String = "",
    val bio: String = "",
    val email: String = "",
    val level: Int = 1,
    val showOnline: Boolean = true,
)

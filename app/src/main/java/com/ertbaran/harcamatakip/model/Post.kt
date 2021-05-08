package com.ertbaran.harcamatakip.model

data class Post(
    val base: String,
    val date: String,
    val rates: Rates,
    val success: Boolean,
    val timestamp: Int
)
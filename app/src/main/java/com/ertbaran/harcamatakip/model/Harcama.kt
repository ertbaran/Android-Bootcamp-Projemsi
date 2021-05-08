package com.ertbaran.harcamatakip.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "harcama_table")
data class Harcama(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val harcamaAdi: String,
    val harcamaFiyat: Int,
    val harcamaKuru: String,
    val harcamaTuru: String
): Parcelable
package com.giftech.quranku_compose.data.model

import com.giftech.quranku_compose.data.model.Ayat

data class Surah(
    val nomor:Int,
    val asma:String,
    val nama:String,
    val jumlahAyat:Int,
    val type:String,
    val arti:String,
    val audio:String,
    val desc:String,
    val listAyat:List<Ayat>? = null
)
package com.giftech.quranku_compose.data.source.remote.dto


import com.giftech.quranku_compose.data.model.Ayat
import com.giftech.quranku_compose.data.model.Surah
import com.google.gson.annotations.SerializedName

data class DetailSurahDto(
    @SerializedName("arti")
    val arti: String,
    @SerializedName("audio")
    val audio: String,
    @SerializedName("ayat")
    val ayat: List<AyatDto>,
    @SerializedName("deskripsi")
    val deskripsi: String,
    @SerializedName("jumlah_ayat")
    val jumlahAyat: Int,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("nama_latin")
    val namaLatin: String,
    @SerializedName("nomor")
    val nomor: Int,
    @SerializedName("tempat_turun")
    val tempatTurun: String
) {
    data class AyatDto(
        @SerializedName("ar")
        val ar: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("idn")
        val idn: String,
        @SerializedName("nomor")
        val nomor: Int,
        @SerializedName("surah")
        val surah: Int,
        @SerializedName("tr")
        val tr: String
    )
}

fun DetailSurahDto.toModel():Surah =
    Surah(
        nomor = nomor,
        asma = nama,
        nama = namaLatin,
        jumlahAyat = jumlahAyat,
        type = tempatTurun,
        arti = arti,
        audio = audio,
        desc = deskripsi,
        listAyat = ayat.map { it.toModel() }
    )

fun DetailSurahDto.AyatDto.toModel(): Ayat =
    Ayat(
        nomorSurah = surah,
        nomor = nomor,
        arab = ar,
        arti = idn
    )
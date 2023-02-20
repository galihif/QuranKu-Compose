package com.giftech.quranku_compose.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.giftech.quranku_compose.data.source.local.entity.SurahEntity
import com.giftech.quranku_compose.data.source.local.room.SurahDao

@Database(entities = [SurahEntity::class], version = 1, exportSchema = false)
abstract class SurahDatabase: RoomDatabase() {

    abstract fun surahDao(): SurahDao

}
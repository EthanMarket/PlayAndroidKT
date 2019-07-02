package com.ethan.core.api.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.ethan.core.api.db.dao.SearchHistoryDao
import com.ethan.core.api.db.entity.SearchHistoryEntity

@Database(entities = [SearchHistoryEntity::class], version = 1)
@TypeConverters(Converters::class)
public abstract class PlayDatabase : RoomDatabase() {
    abstract fun getSearchHistoryDao(): SearchHistoryDao
}


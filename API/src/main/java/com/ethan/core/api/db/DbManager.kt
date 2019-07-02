package com.ethan.core.api.db

import android.arch.persistence.room.Room
import com.ethan.core.api.base.PlayApplication

class DbManager private constructor() {
    private val mDbHelper: PlayDatabase =
        Room.databaseBuilder(PlayApplication.context, PlayDatabase::class.java, "play_android.db").allowMainThreadQueries().build()

    companion object {
        val instance: DbManager by lazy { DbManager() }
    }

    fun getDbHelper(): PlayDatabase {
        return mDbHelper
    }

}
package com.ethan.core.api.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.ethan.core.api.db.Converters


@Entity(tableName = "SearchHistory")
@TypeConverters(
    Converters::class
)
class SearchHistoryEntity(
    var name: String,
    var time: String
) {

    @ColumnInfo(name = "history_name")
    var historyName = name
    @ColumnInfo(name = "history_time")
    var historyTime = time

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    override fun toString(): String {
        return "SearchHistoryEntity(historyName='$historyName', historyTime='$historyTime', id=$id)"
    }


}
package com.ethan.core.api.db.dao

import android.arch.persistence.room.*
import com.ethan.core.api.db.entity.SearchHistoryEntity


@Dao
interface SearchHistoryDao : BaseDao<SearchHistoryEntity> {

    @Query("SELECT  * FROM SearchHistory order by time Desc  limit 5 ")
    fun selectSearchHistoryList(): MutableList<SearchHistoryEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchHistory(searchHistoryEntity: SearchHistoryEntity)

    @Query("delete from SearchHistory")
    fun deleteSearchHistory()
}
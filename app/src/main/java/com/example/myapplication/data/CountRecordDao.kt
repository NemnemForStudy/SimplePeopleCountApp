package com.example.myapplication.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CountRecordDao {
    @Insert
    suspend fun insert(record: CountRecord)

    @Query("SELECT * FROM count_records ORDER BY date DESC")
    fun getAllRecords(): Flow<List<CountRecord>>

    @Query("DELETE FROM count_records WHERE id NOT IN (SELECT id FROM count_records ORDER BY date DESC LIMIT 1)")
    suspend fun deleteExceptMostRecent()
}
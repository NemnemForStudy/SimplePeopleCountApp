package com.example.myapplication.repository

import com.example.myapplication.data.CountRecord
import com.example.myapplication.data.CountRecordDao
import kotlinx.coroutines.flow.Flow
import java.util.Date

class CountRepository(private val countRecordDao: CountRecordDao) {
    fun getAllRecords(): Flow<List<CountRecord>> {
        return countRecordDao.getAllRecords()
    }

    suspend fun saveRecord(count: Int) {
        val record = CountRecord(date = Date(), count = count)
        countRecordDao.insert(record)
    }

    suspend fun deleteRecordsExceptMostRecent() {
        countRecordDao.deleteExceptMostRecent()
    }
}
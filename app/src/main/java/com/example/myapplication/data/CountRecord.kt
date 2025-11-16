package com.example.myapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "count_records")
data class CountRecord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: Date,
    val count: Int
)
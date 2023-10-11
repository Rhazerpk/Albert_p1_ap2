package com.kotlin.primerparcial_albert.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.primerparcial_albert.data.local.dao.DivisionDao

@Database(
    entities = [Ticket::class ],
    version = 4,
    exportSchema = false
)
abstract class DivisionDb : RoomDatabase() {
    abstract fun divisionDao(): DivisionDao
}
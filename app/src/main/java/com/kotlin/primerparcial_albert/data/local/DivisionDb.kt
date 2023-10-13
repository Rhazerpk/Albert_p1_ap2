package com.kotlin.primerparcial_albert.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.primerparcial_albert.data.local.dao.DivisionDao
import com.kotlin.primerparcial_albert.data.local.entities.Division

@Database(
    entities = [Division::class ],
    version = 6,
    exportSchema = false
)
abstract class DivisionDb : RoomDatabase() {
    abstract fun divisionDao(): DivisionDao
}
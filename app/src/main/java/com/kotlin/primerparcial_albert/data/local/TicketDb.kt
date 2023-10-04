package com.kotlin.primerparcial_albert.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kotlin.primerparcial_albert.data.local.dao.TicketDao
import com.kotlin.primerparcial_albert.data.local.entities.Ticket

@Database(
    entities = [Ticket::class ],
    version = 4,
    exportSchema = false
)
abstract class TicketDb : RoomDatabase() {
    abstract fun ticketDao(): TicketDao
}
package com.kotlin.primerparcial_albert.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.primerparcial_albert.data.local.entities.Ticket
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(ticket: Ticket)

    @Query(
        """
        SELECT * 
        FROM Tickets 
        WHERE ticketId=:id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): Ticket?

    @Delete
    suspend fun delete(ticket: Ticket)

    @Query("SELECT * FROM Tickets")
    fun getAll(): Flow<List<Ticket>>
}
package com.kotlin.primerparcial_albert.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.kotlin.primerparcial_albert.data.local.entities.Division
import kotlinx.coroutines.flow.Flow

@Dao
interface DivisionDao {
    @Upsert
    suspend fun save(division: Division)
    @Query(
        """
        SELECT * 
        FROM Divisiones 
        WHERE dividirId=:id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): Division
    @Delete
    suspend fun delete(division: Division)

    @Query("""
        SELECT *
        FROM Divisiones
        ORDER BY nombre
    """)
    fun getAll(): Flow<List<Division>>
}
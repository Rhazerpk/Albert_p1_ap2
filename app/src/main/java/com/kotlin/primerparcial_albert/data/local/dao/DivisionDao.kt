package com.kotlin.primerparcial_albert.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kotlin.primerparcial_albert.data.local.entities.Division

@Dao
interface DivisionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(division: Division)
    @Query(
        """
        SELECT * 
        FROM Divisiones 
        WHERE id=:id  
        LIMIT 1
        """
    )
    suspend fun find(id: Int): Division?
    @Delete
    suspend fun delete(division: Division)

    @Query("SELECT * FROM Divisiones")
    suspend fun getAll(): List<Division>
}
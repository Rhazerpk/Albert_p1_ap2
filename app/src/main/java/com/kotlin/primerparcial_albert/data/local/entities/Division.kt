package com.kotlin.primerparcial_albert.data.local.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Divisiones")
data class Division(
    @PrimaryKey(autoGenerate = true)
    val dividirId: Int? = null,
    val nombre: String = "",
    val dividendo: Float = 0.0f,
    val divisor: Float = 0.0f,
    val cociente: Float = 0.0f,
    val residuo: Float = 0.0f
)
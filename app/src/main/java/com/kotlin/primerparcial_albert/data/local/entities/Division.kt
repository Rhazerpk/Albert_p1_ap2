package com.kotlin.primerparcial_albert.data.local.entities
import androidx.room.Entity

@Entity(tableName = "Calculadora")
data class Calculadora(
    val nombre: String = "",
    val dividendo: Float = 0.0f,
    val divisor: Float = 0.0f,
    val cociente: Float = 0.0f,
    val Residuo: Float = 0.0f
)
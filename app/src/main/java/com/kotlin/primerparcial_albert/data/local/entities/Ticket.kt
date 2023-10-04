package com.kotlin.primerparcial_albert.data.local.entities
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tickets")
data class Ticket(
    @PrimaryKey
    val ticketId: Int?=null ,
    var cliente: String = "",
    var solicitadoPor: String = "",
    var asunto: String = "",
    var solicitud: String = ""
)
package com.kotlin.primerparcial_albert.data.repository

import com.kotlin.primerparcial_albert.data.local.TicketDb
import com.kotlin.primerparcial_albert.data.local.entities.Ticket
import javax.inject.Inject

class TicketRepository @Inject constructor(
    private val ticketDb: TicketDb
) {
    suspend fun  saveTicket(ticket: Ticket) = ticketDb.ticketDao().save(ticket)
    fun getAll() = ticketDb.ticketDao().getAll()
}
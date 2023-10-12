package com.kotlin.primerparcial_albert.ui.division

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.primerparcial_albert.data.local.entities.Division
import com.kotlin.primerparcial_albert.data.repository.DivisionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DivisionViewModel @Inject constructor(
    private val divisionRepository: DivisionRepository
): ViewModel() {

    var Nombre by mutableStateOf("")
    var Dividendo by mutableStateOf(0)
    var Divisor by mutableStateOf(0)
    var Cociente by mutableStateOf(0)
    var Residuo by mutableStateOf(0)

    var nombreError by mutableStateOf(true)
    var dividendoError by mutableStateOf(true)
    var divisorError by mutableStateOf(true)
    var cocienteError by mutableStateOf(true)
    var residuoError by mutableStateOf(true)


    private fun Validar() {
        nombreError = Nombre.isEmpty()
        dividendoError = Dividendo == 0
        divisorError = Divisor == 0
    }

    val divisiones: StateFlow<List<Division>> = divisionRepository.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun calcularDivision() {
        Validar()

        if (!nombreError && !dividendoError && !divisorError) {
            if (Divisor != 0) {
                val dividendoFloat = Dividendo.toFloat()
                val divisorFloat = Divisor.toFloat()
                val cocienteFloat = dividendoFloat / divisorFloat
                val residuoFloat = dividendoFloat % divisorFloat

                Cociente = cocienteFloat.toInt()
                Residuo = residuoFloat.toInt()

                cocienteError = false
                residuoError = false
            } else {
                cocienteError = true
                residuoError = true
            }
        }
    }

    fun saveDivision() {
        Validar()

        if (!nombreError && !dividendoError && !divisorError && !cocienteError && !residuoError) {
            val division = Division(
                nombre = Nombre,
                dividendo = Dividendo.toFloat(),
                divisor = Divisor.toFloat(),
                cociente = Cociente.toFloat(),
                residuo = Residuo.toFloat()
            )
            viewModelScope.launch {
                divisionRepository.save(division)
            }
        }
    }

    fun deleteDivision(division: Division) {
        viewModelScope.launch {
            divisionRepository.delete(division)
        }
    }

}
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

    var isValidNombre by mutableStateOf(true)
    var isValidDividendo by mutableStateOf(true)
    var isValidDivisor by mutableStateOf(true)
    var isValidCociente by mutableStateOf(true)
    var isValidResiduo by mutableStateOf(true)

    var errorDividendo by mutableStateOf("")
    var errorDivisor by mutableStateOf("")
    var errorCociente by mutableStateOf("")
    var errorResiduo by mutableStateOf("")

    private fun Validar(): Boolean {
        isValidNombre = Nombre.isNotBlank()
        isValidDividendo = Dividendo != 0
        isValidDivisor = Divisor != 0 && Divisor <= Dividendo
        isValidCociente = Cociente >= 0
        isValidResiduo = Residuo <= Dividendo && Residuo != 0

        errorDividendo = if (isValidDividendo) "" else "Dividiendo requerido"
        errorDivisor = when {
            !isValidDivisor -> if (Divisor == 0) "Divisor requerido" else "Divisor incorrecto"
            else -> ""
        }
        errorCociente = if (isValidCociente) "" else "Cociente requerido"
        errorResiduo = when {
            !isValidResiduo -> if (Residuo == 0) "Residuo requerido" else "Residuo inválido"
            else -> ""
        }

        return isValidNombre && isValidDividendo && isValidDivisor && isValidCociente && isValidResiduo
    }

    val divisiones: StateFlow<List<Division>> = divisionRepository.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun saveDivision() {
        if (Validar()) {
            val division = Division(
                nombre = Nombre,
                dividendo = Dividendo.toFloat(),
                divisor = Divisor.toFloat(),
                cociente = Cociente.toFloat(),
                residuo = Residuo.toFloat()
            )
            viewModelScope.launch {
                divisionRepository.save(division)
                limpiar()
            }
        }
    }

    fun deleteDivision(division: Division) {
        viewModelScope.launch {
            divisionRepository.delete(division)
        }
    }

    fun limpiar() {
        Nombre = ""
        Dividendo = 0
        Divisor = 0
        Cociente = 0
        Residuo = 0
    }

}
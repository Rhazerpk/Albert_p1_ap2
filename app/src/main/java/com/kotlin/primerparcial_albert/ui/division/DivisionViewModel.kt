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
    var isCorrectDivision by mutableStateOf(true)

    var errorDividendo by mutableStateOf("")
    var errorDivisor by mutableStateOf("")
    var errorCociente by mutableStateOf("")
    var errorResiduo by mutableStateOf("")

    private fun validate(): Boolean {

        var verifyDividendo:Int?
        isValidNombre = Nombre.isNotEmpty()
        isValidDividendo = Dividendo > 0
        isValidDivisor = Divisor > 0
        isValidCociente = Cociente > 0
        isValidResiduo = Residuo > -1

        verifyDividendo = Cociente * Divisor
        verifyDividendo += Residuo
        isCorrectDivision = verifyDividendo == Dividendo

        if(verifyDividendo == Dividendo)
            isCorrectDivision = true
        else{

            var result:Int = Dividendo / Divisor

            if(result != Cociente)
                errorCociente = "El cociente está incorrecto"

            result = Dividendo % Divisor

            if(result != Residuo)
                errorResiduo = "El residuo está incorrecto"
        }

        return !(Nombre == "" || Dividendo <= 0 || Divisor <= 0 || Cociente <= 0 || Residuo < 0 || !isCorrectDivision)
    }

    val divisions: StateFlow<List<Division>> = divisionRepository.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun saveDivision() {
        if (validate()) {
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
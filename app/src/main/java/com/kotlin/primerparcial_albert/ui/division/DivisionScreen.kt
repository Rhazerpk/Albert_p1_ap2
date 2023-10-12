package com.kotlin.primerparcial_albert.ui.division
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DivisionScreen(
    viewModel: DivisionViewModel = hiltViewModel()
) {

    val focusManager = LocalFocusManager.current
    val divisiones by viewModel.prestamos.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = viewModel.Nombre,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { viewModel.Nombre = it },
            label = { Text("Nombre") },
            isError = viewModel.nombreError,
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
        )

        Row {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = viewModel.Dividendo.toString(),
                onValueChange = { viewModel.Dividendo = it.toIntOrNull() ?: 0 },
                label = { Text("Dividendo") },
                isError = viewModel.dividendoError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),

                )

            Spacer(modifier = Modifier.height(16.dp))
            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                value = viewModel.Divisor.toString(),
                onValueChange = { viewModel.Divisor = it.toIntOrNull() ?: 0 },
                label = { Text("Divisor") },
                isError = viewModel.divisorError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = { focusManager.moveFocus(FocusDirection.Down) }
                ),
            )
        }

        Button(
            onClick = {
                viewModel.calcularDivision()
                viewModel.saveDivision()
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
                Text("Guardar")
            }
        }

        Row {
            Text(text = "Historial de resultado", style = MaterialTheme.typography.titleMedium)
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(divisiones) { division ->
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "${division.nombre}",
                            style = TextStyle(fontWeight = FontWeight.Bold),
                            fontSize = 18.sp
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Dividendo: ${division.dividendo}")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Divisor: ${division.divisor}")
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Cociente: ${division.cociente}")
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = "Residuo: ${division.residuo}")
                    }

                    Divider(modifier = Modifier.fillMaxWidth())
                }

                Button(
                    onClick = {
                        viewModel.deleteDivision(division)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    Text("Eliminar")
                }
            }
        }
//        Text(
//            text = "Cociente: ${viewModel.Cociente}",
//            modifier = Modifier.padding(top = 16.dp)
//        )
//
//        Text(
//            text = "Residuo: ${viewModel.Residuo}",
//            modifier = Modifier.padding(top = 8.dp)
//        )
        if (viewModel.nombreError || viewModel.dividendoError || viewModel.divisorError) {
            Text(
                text = "Por favor, complete todos los campos.",
                color = Color.Red,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}


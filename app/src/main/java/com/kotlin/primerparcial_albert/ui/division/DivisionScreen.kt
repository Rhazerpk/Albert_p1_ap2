package com.kotlin.primerparcial_albert.ui.division

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DivisionScreen(
    viewModel: DivisionViewModel = hiltViewModel()
) {
    val focusManager = LocalFocusManager.current
    val divisions by viewModel.divisions.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = { Text(text = "Aprende a dividir") }, actions = {
            IconButton(onClick = { viewModel.limpiar() }) {
                Icon(
                    imageVector = Icons.Default.Refresh, contentDescription = "Refresh"
                )
            }
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = viewModel.Nombre,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                onValueChange = { viewModel.Nombre = it },
                label = { Text("Nombre") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
            )

            if (!viewModel.isValidNombre) {
                Text(text = "El nombre es requerido.", color = Color.Red, fontSize = 12.sp)
            }

            Row {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    OutlinedTextField(
                        value = viewModel.Dividendo.toString(),
                        onValueChange = { viewModel.Dividendo = it.toIntOrNull() ?: 0 },
                        label = { Text("Dividendo") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                    )
                    if (viewModel.errorDividendo.isNotEmpty()) {
                        Text(text = viewModel.errorDividendo, color = Color.Red)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    OutlinedTextField(
                        value = viewModel.Divisor.toString(),
                        onValueChange = { viewModel.Divisor = it.toIntOrNull() ?: 0 },
                        label = { Text("Divisor") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                    )
                    if (viewModel.Divisor != 0) {
                        Text(text = viewModel.errorDivisor, color = Color.Red)
                    }
                }
            }

            Row {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    OutlinedTextField(
                        value = viewModel.Cociente.toString(),
                        onValueChange = { viewModel.Cociente = it.toIntOrNull() ?: 0 },
                        label = { Text("Cociente") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                    )
                    if (viewModel.errorCociente.isNotEmpty()) {
                        Text(text = viewModel.errorCociente, color = Color.Red)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Spacer(modifier = Modifier.width(8.dp))

                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                ) {
                    OutlinedTextField(
                        value = viewModel.Residuo.toString(),
                        onValueChange = { viewModel.Residuo = it.toIntOrNull() ?: 0 },
                        label = { Text("Residuo") },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number, imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                    )
                    if (viewModel.Residuo != 0) {
                        Text(text = viewModel.errorResiduo, color = Color.Red)
                    }
                }
            }

            Button(
                onClick = {
                    viewModel.saveDivision()
                }, modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
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

            Divider(modifier = Modifier.fillMaxWidth())

            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(divisions) { division ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "${division.nombre}",
                                style = TextStyle(fontWeight = FontWeight.Bold),
                                fontSize = 18.sp
                            )
                            Row(
                                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                            ) {
                                Text(text = "Dividendo: ${division.dividendo}")
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Divisor: ${division.divisor}")
                            }
                            Row(
                                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
                            ) {
                                Text(text = "Cociente: ${division.cociente}")
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(text = "Residuo: ${division.residuo}")
                            }
                        }
                        Button(
                            onClick = {
                                viewModel.deleteDivision(division)
                            }, modifier = Modifier
                                .size(60.dp)
                                .padding(top = 8.dp)
                        ) {
                            Text("X", fontSize = 20.sp)
                        }
                    }

                    Divider(modifier = Modifier.fillMaxWidth())

                }
            }
        }
    }
}

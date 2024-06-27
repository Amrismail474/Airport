package com.asi.airport.UIi

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asi.airport.data.AirportViewmodel
import com.asi.airport.ui.theme.AirportTheme


@Composable
fun MainScreen(
    viewModel: AirportViewmodel = hiltViewModel()
){


    val uiState by viewModel.uistate.collectAsState()
    var code by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopBar(title = "FLight Search")}
    ) {padding->
        Column(modifier = Modifier.padding(padding)) {
            ContentScreen(
                code = code,
                onValueChange = { code = it },
                contentPadding = padding,
                onClick = {
                    if (code.isNotEmpty()) {
                        viewModel.callAirport(code)
                        Log.d("code", code)

                    }
                }
            )

            uiState.airport?.let { airport ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    Text(text = "Airport: ${airport.name}")
                    Text(text = "IATA Code: ${airport.iata_code}")
                    Text(text = "Passengers: ${airport.passengers}")
                }
            }

            uiState.errorMessage?.let { errorMessage ->
                Text(
                    text = "Error: $errorMessage",
                    color = androidx.compose.material3.MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(padding)
                )
            }}}}







@Composable
fun ContentScreen(
    modifier : Modifier = Modifier,
    code: String,
    onValueChange : (String)-> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    onClick : (String) -> Unit,

){
    Box(modifier.fillMaxSize()){
        Column(
            modifier.padding(30.dp).align(Alignment.TopCenter),verticalArrangement= Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextField(value = code,
                onValueChange = onValueChange)

            Button(onClick = {onClick(code)},
                modifier = modifier.width(200.dp).padding(16.dp).wrapContentSize()
            ) {
                Text(text = "Search",modifier.padding(10.dp))
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String){
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors()
    )

}


@Preview
@Composable
fun preview(){
    AirportTheme {
        ContentScreen(onValueChange = {},onClick = {}, code="IJK")
    }
}
package com.example.gatoapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SecondScreen(goToFirstScreen:()-> Unit){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)){
        Button(onClick = { goToFirstScreen()}) {
            Row(){
                Text(
                    text = "Go to First",
                    modifier = Modifier
                )
                Icon(Icons.Filled.ArrowBack, "go back")
            }
        }
    }
}
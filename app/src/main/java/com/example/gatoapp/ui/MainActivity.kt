package com.example.gatoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gatoapp.ui.GatoViewModel
import com.example.gatoapp.ui.theme.GatoAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GatoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CatFactLayout()
                }
            }
        }
    }
}

@Composable
fun CatFactLayout(modifier: Modifier = Modifier) {
    val catFactViewModel: GatoViewModel = koinViewModel()
    val catFact by catFactViewModel.fact
    LaunchedEffect(key1 = Unit) {
        catFactViewModel.getCatFact()
    }
    Column(modifier = modifier) {
        Text(
            text = "$catFact!",
            modifier = Modifier
                .border(1.dp, Color.Blue, RoundedCornerShape(15.dp))
                .shadow(16.dp, RoundedCornerShape(15.dp))
                .background(color = MaterialTheme.colorScheme.background)
                .padding(5.dp)
        )
        Row {
            Button(onClick = { catFactViewModel.getPrevious() }) {
                Text(
                    text = "Previous",
                    modifier = Modifier
                )
            }
            Button(onClick = { catFactViewModel.getNext() }) {
                Text(
                    text = "Next",
                    modifier = Modifier
                )
            }
        }
    }
}

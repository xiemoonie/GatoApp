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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.gatoapp.ui.GatoViewModel
import com.example.gatoapp.ui.ScreenType
import com.example.gatoapp.ui.SecondScreen
import com.example.gatoapp.ui.theme.GatoAppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var currentScreen by mutableStateOf(ScreenType.main)
        super.onCreate(savedInstanceState)
        setContent {
            GatoAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if(currentScreen == ScreenType.main){
                        CatFactLayout(goToSecondScreen = {currentScreen = ScreenType.secondScreen})
                    }else{
                        SecondScreen(goToFirstScreen = {currentScreen= ScreenType.main})
                    }
                }
            }
        }
    }
}

@Composable
fun CatFactLayout(modifier: Modifier = Modifier, goToSecondScreen:()-> Unit) {
    val catFactViewModel: GatoViewModel = koinViewModel()
    val catFact by catFactViewModel.fact
    val breeds by catFactViewModel.breed
    val checkBox by catFactViewModel.checkboxes
    LaunchedEffect(key1 = Unit) {
        catFactViewModel.getCatFact()
        catFactViewModel.updateBreeds()
        catFactViewModel.getBreeds()
    }
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
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
        Column {
            breeds.forEach() {
                breed->
                Row{
                    Text(
                        text = "The bread is ${breed.breed}. From ${breed.country}!",
                        modifier = Modifier
                            .border(1.dp, Color.Blue, RoundedCornerShape(15.dp))
                            .shadow(16.dp, RoundedCornerShape(15.dp))
                            .background(color = MaterialTheme.colorScheme.background)
                            .padding(5.dp)
                    )
                    Checkbox(
                        checked = checkBox.contains(breed.breed),
                        onCheckedChange = {
                            if(it){
                                catFactViewModel.selectBreed(breed.breed)
                            }else{
                                catFactViewModel.unselectBreed(breed.breed)
                            }
                        }
                    )
                }
            }
        }
        Column {
            Button(onClick = { catFactViewModel.deleteBreed() }) {
                Text(
                    text = "Erase breeds",
                    modifier = Modifier
                )
            }
            Button(onClick = goToSecondScreen) {
                Text(
                    text = "Go Second Screen",
                    modifier = Modifier
                )
                Icon(Icons.Filled.ArrowForward, "go back")
            }
        }
    }
}

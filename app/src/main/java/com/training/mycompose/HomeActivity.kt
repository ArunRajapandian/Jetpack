package com.training.mycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.training.mycompose.ui.theme.MyComposeTheme

class HomeActivity : ComponentActivity() {
    var phNo=""
    var passWord =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            getIntentValues()
            Column( modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,) {
                cusText(phNo)
            }

        }
    }

    private fun getIntentValues() {
        if (intent.hasExtra("phone")){
            phNo = intent.getStringExtra("phone")!!
        }
    }
}

@Composable
fun cusText(string:String){
    Text(text = string, textAlign = TextAlign.Center)
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    MyComposeTheme {
       cusText(string = "hai")
    }
}
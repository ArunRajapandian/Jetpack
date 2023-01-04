package com.training.mycompose

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.training.mycompose.ui.theme.MyComposeTheme
import kotlinx.coroutines.delay
import android.window.SplashScreen as SplashScreen1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
                    com.training.mycompose.Navigation()
                    nextActivity(this)
                }
            }
        }
    }

    private fun nextActivity(con:Context) {
        lifecycleScope.launchWhenCreated {
            delay(3000L)
            var intent = Intent(con,SecondActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen"){
        composable("splash_screen"){
            SplashScreen(navController= navController)

        }

/*
        composable("main_screen"){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "Main Screen", color = Color.Black, fontSize = 24.sp)
            }
        }
*/
    }

}

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    LaunchedEffect(key1= true){
        scale.animateTo(targetValue = 0.7f, animationSpec = tween(durationMillis = 800, easing = {
            OvershootInterpolator(4f).getInterpolation(it)
        }))
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        // Text(text = "welcome", modifier = Modifier.scale(scale.value))
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "", Modifier.height(250.dp).scale(scale.value),

            )
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeTheme {
        Navigation()
    }
}
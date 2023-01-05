package com.training.mycompose

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

class SecondActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginScreen()

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    LoginScreen()


}

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier
            .height(10.dp)
            .fillMaxWidth())
        Image(
            painter = painterResource(id = R.drawable.login),
            contentDescription = "", Modifier.height(250.dp)
        )

        SimpleText("Welcome to Login")
        commonSpace()
        inputFields()

        Text(text = "or", color = Blue, fontSize = 16.sp, modifier = Modifier.padding(top = 15.dp))
        commonSpace()
        socialLogin()
    }
}


@Composable
fun SimpleText(s: String) {
    Text(
        text = s,
        style = TextStyle(
            shadow = Shadow(
                color = Blue,
                offset = Offset(5f, 5f),
                blurRadius = 5f
            )
        ),
        fontFamily = FontFamily.Cursive,
        fontSize = 28.sp,
        letterSpacing = .2.em,
        modifier = Modifier.padding(top = 20.dp)
    )

}

@Composable
fun commonSpace(){
    Spacer(modifier = Modifier.height(10.dp))
}

@Composable
fun inputFields() {
    Column(Modifier.padding(16.dp)) {
        var phoneNumber = remember { mutableStateOf(TextFieldValue()) }
        var passWord = remember { mutableStateOf(TextFieldValue()) }
        OutlinedTextField(
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            placeholder = { Text(text = "phone ")} ,
            leadingIcon = { Icon(imageVector = Icons.Filled.Phone, contentDescription = null, tint = colorResource(
                id = R.color.custom_color
            )) },
            label = { Text(text = "Phone")},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Blue,
                unfocusedBorderColor = Gray),
            shape = RoundedCornerShape(30)
        )

        commonSpace()

        OutlinedTextField(
            value = passWord.value,
            onValueChange = { passWord.value = it },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = { Icon(imageVector = Icons.Filled.Lock, contentDescription = null, tint = colorResource(
                id = R.color.custom_color
            )) },
            label = { Text(text = "Password")},
            placeholder = { Text(text = "password")} ,
            shape = RoundedCornerShape(30)

        )
        commonSpace()
        submitButton(LocalContext.current,phoneNumber,passWord)

    }
}

@Composable
fun submitButton(
    context: Context,
    phoneNumber: MutableState<TextFieldValue>,
    passWord: MutableState<TextFieldValue>
){

    Button(
        onClick = {
            val nextActivity = Intent(context,HomeActivity::class.java)
            nextActivity.putExtra("phone",phoneNumber.value.text )
            nextActivity.putExtra("password",passWord.value.text )
            context.startActivity(nextActivity)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 20.dp, end = 20.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.custom_color)),
        shape = CircleShape,
    )
    {
        Text(text = "Submit",
            color = White,)
    }
}

@Composable
fun socialLogin(){
    Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { /*TODO*/ }, shape = CircleShape,colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(
            id = R.color.custom_color
        ))) {
            Image(

                painter = painterResource(id = R.drawable.google),
                contentDescription = "Image",
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)

            )

            Text(text = "oogle", color= White,modifier = Modifier.padding(start = 5.dp))
        }

        Button(onClick = { /*TODO*/ }, shape = CircleShape,colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(
            id = R.color.custom_color
        ))) {
            Image(

                painter = painterResource(id = R.drawable.fb),
                contentDescription = null,
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)

            )
            Text(text = "acebook", color= White, modifier = Modifier.padding(start = 5.dp))
        }

    }
}





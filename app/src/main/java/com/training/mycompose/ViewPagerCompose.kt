package com.training.mycompose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
public  fun viewPager(){
    Column() {
        val pageList= arrayListOf(
            PagerModel(R.drawable.captain_america,"Captain Marvel is an extraterrestrial Kree warrior who finds herself caught in the battle between people and the Skrulls."),
            PagerModel(R.drawable.thor," Thor (Chris Hemsworth) will soon inherit the throne of Asgard from his aging father."),
            PagerModel(R.drawable.wakanda,"The people of Wakanda fight to protect their home from intervening world powers as they mourn the death of King T'Challa."),
        )
        val pageState = rememberPagerState()
        HorizontalPager(count =pageList.size, state = pageState, modifier = Modifier
            .fillMaxWidth()
            .width(1.dp),) {
                page ->
            PagerUi(pagerModel = pageList[page])

        }
        HorizontalPagerIndicator(pagerState = pageState, modifier = Modifier.padding(20.dp).align(alignment = Alignment.CenterHorizontally), activeColor = colorResource(
            id = R.color.custom_color
        ), inactiveColor = colorResource(id = R.color.custom_Grey))
    }


}

@Composable
fun PagerUi(pagerModel: PagerModel){
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id =pagerModel.image ), contentDescription =null, modifier = Modifier.size(200.dp) )
        Text(text = pagerModel.description, fontFamily = FontFamily.Cursive,modifier = Modifier.padding(10.dp).align(alignment = Alignment.CenterHorizontally) )
    }
}
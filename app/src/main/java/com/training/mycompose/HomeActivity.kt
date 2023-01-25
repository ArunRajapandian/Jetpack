package com.training.mycompose

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

class HomeActivity : ComponentActivity() {
    var phNo = ""
    var passWord = ""

    var imagesArray: ArrayList<Int> = ArrayList()

    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            getIntentValues()
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                customText(phNo)
                commonSpace()
                viewPager()
                commonSpace()
                directScroll()
            }


        }
    }

    private fun getIntentValues() {
        if (intent.hasExtra("phone")) {
            phNo = intent.getStringExtra("phone")!!

            imagesArray.clear()

        }
    }
}

@Composable
fun directScroll() {

    val movies = remember { DataProvider.movieList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = movies,
            itemContent = { movieListItem(movieDetails = it)
            })
        itemsIndexed(DataProvider.movieList){index, item ->
            Log.d("selected_Data",item.title)
        }

    }
}


@Composable
fun likeAsRecycler(imagesArray: ArrayList<String>, data: IntRange) {
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.verticalScroll(scrollState)) {
        for (i in data) {

            Text(
                "User Name $i",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .padding(5.dp)
                    .clickable { "$i Selected" }

            )
            LazyColumnDemo2(i.toString())
            Divider(color = colorResource(id = R.color.custom_dark), thickness = 5.dp)

        }
    }

}

@Composable
fun LazyColumnDemo2(selectedItem: String) {
    Toast.makeText(LocalContext.current, selectedItem, Toast.LENGTH_LONG).show()
}

@Composable
fun customText(string: String) {
    Text(text = string, textAlign = TextAlign.Center)
}

@Composable
fun movieListItem(movieDetails: MovieDetails) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth(),
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(20)),

    ) {

        Row {
            movieImage(movieDetails)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)
                    .clickable {
                    /*
                        Toast
                            .makeText(context, movieDetails.title, Toast.LENGTH_LONG)
                            .show()
*/
                    }
            ) {

                Text(text = movieDetails.title, style = typography.h6)
                Text(text ="Genre: "+ movieDetails.genres, style = typography.caption)

            }

        }
    }
}

@Composable
private fun movieImage(movieDetails: MovieDetails) {
    val context = LocalContext.current
    Image(
        painter = painterResource(id = movieDetails.movieImage),
        contentDescription = null,
        contentScale = ContentScale.Inside,

        modifier = Modifier
            .padding(8.dp)
            .size(84.dp)
            .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
            .clickable {
                Toast.makeText(context, movieDetails.id.toString(), Toast.LENGTH_LONG).show()
            }
    )
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    //likeAsRecycler()
}
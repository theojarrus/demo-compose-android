package com.theost.composeapp.ui.screens.dashboard.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.theost.composeapp.ui.screens.dashboard.model.FilmUi

@Composable
fun FilmView(item: FilmUi, listener: ((Int) -> Unit)? = null) {
    Card(
        elevation = 20.dp,
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 4.dp, bottom = 6.dp, start = 16.dp, end = 16.dp)
            .clickable { listener?.invoke(item.id) }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(end = 8.dp).fillMaxWidth()
        ) {
            AsyncImage(
                model = item.posterUrl,
                contentDescription = null,
                modifier = Modifier.height(180.dp)
            )
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
                    .fillMaxHeight()
            ) {
                Text(text = item.name, fontWeight = Bold)
                Text(text = item.rating)
                Text(text = item.countries, modifier = Modifier.padding(top = 4.dp))
                Text(text = item.genres)
                Text(text = item.year)
            }
        }
    }
}

@Composable
@Preview
fun FilmViewPreview() {
    FilmView(
        item = FilmUi(
            id = 0,
            name = "Фильм (Film)",
            year = "2000",
            description = "Однажды что-то случилось",
            countries = "Швеция, Дания",
            genres = "триллер",
            rating = "7.9 (432)",
            posterUrl = "",
            isFavourite = false
        )
    )
}

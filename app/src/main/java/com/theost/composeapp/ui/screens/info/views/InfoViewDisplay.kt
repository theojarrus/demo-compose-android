package com.theost.composeapp.ui.screens.info.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.theost.composeapp.presentation.info.InfoState

@Composable
fun InfoViewDisplay(viewState: InfoState) {
    Surface(modifier = Modifier.fillMaxHeight()) {
        Card(
            shape = RoundedCornerShape(15.dp),
            elevation = 12.dp,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SubcomposeAsyncImage(
                    model = viewState.data?.posterUrl,
                    loading = { CircularProgressIndicator() },
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                )
                Column(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(viewState.data?.name.orEmpty(), fontWeight = FontWeight.Bold)
                    Text(
                        viewState.data?.description.orEmpty(),
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

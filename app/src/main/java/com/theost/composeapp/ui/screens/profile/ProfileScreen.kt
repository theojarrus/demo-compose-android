package com.theost.composeapp.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.theost.composeapp.R
import com.theost.composeapp.presentation.profile.ProfileViewModel

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    profileViewModel: ProfileViewModel
) {
    Surface(modifier.fillMaxSize()) {
        val viewState = profileViewModel.state.observeAsState()
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxHeight()
        ) {
            viewState.value?.data?.let { user ->
                SubcomposeAsyncImage(
                    model = user.avatar,
                    loading = { CircularProgressIndicator() },
                    contentDescription = stringResource(R.string.profile),
                    modifier = Modifier
                        .size(200.dp)
                        .clip(RoundedCornerShape(15.dp))
                )
                Text(
                    text = user.name,
                    fontSize = 22.sp,
                    fontWeight = Bold,
                    modifier = modifier.padding(top = 10.dp)
                )
                Text(
                    text = user.email,
                    fontSize = 18.sp,
                    modifier = modifier.padding(top = 2.dp)
                )
            }
        }
    }
}

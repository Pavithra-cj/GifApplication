package com.itzcorpio.gifapplication.presentation.gif_list.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.itzcorpio.gifapplication.presentation.components.GifImage

@Composable
fun GifListScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        GifImage(
            model = "https://media.giphy.com/media/3oEjI6SIIHBdRxXI40/giphy.gif",
            contentDescription = "Loading animation",
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        val context = LocalContext.current
        GifImage(
            model = ImageRequest.Builder(context)
                .data("https://media.giphy.com/media/l0HlNQ03J5JxX6lva/giphy.gif")
                .crossfade(true)
                .diskCachePolicy(CachePolicy.ENABLED)
                .memoryCachePolicy(CachePolicy.ENABLED)
                .build(),
            contentDescription = "Animated GIF with controls",
            modifier = Modifier
                .size(180.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
    }
}
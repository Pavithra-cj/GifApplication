package com.itzcorpio.gifapplication.presentation.gif_list.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.request.CachePolicy
import coil.request.ImageRequest
import com.itzcorpio.gifapplication.presentation.components.GifImage
import com.itzcorpio.gifapplication.presentation.gif_list.viewmodel.GifViewModel

@Composable
fun GifListScreen(
    viewModel: GifViewModel? = null,
    onShowGiphyDialog: () -> Unit = {}
) {
    val selectedGifs by (viewModel?.selectedGifs?.collectAsState() ?: androidx.compose.runtime.remember { androidx.compose.runtime.mutableStateOf(emptyList()) })

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Spacer(modifier = Modifier.height(16.dp))
        
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(selectedGifs) { gif ->
                GifImage(
                    model = gif.url,
                    contentDescription = gif.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(vertical = 8.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
            }
        }

        if (selectedGifs.isEmpty()) {
            // Original sample GIFs if none selected
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

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onShowGiphyDialog,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Select GIF from Giphy")
        }
    }
}
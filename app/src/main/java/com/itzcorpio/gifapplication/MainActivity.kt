package com.itzcorpio.gifapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.giphy.sdk.ui.GPHContentType
import com.giphy.sdk.ui.Giphy
import com.giphy.sdk.ui.views.GiphyDialogFragment
import com.itzcorpio.gifapplication.data.repository.GiphyRepositoryImpl
import com.itzcorpio.gifapplication.domain.usecase.GetSelectedGifsUseCase
import com.itzcorpio.gifapplication.domain.usecase.SaveGifUseCase
import com.itzcorpio.gifapplication.presentation.gif_list.screen.GifListScreen
import com.itzcorpio.gifapplication.presentation.gif_list.viewmodel.GifViewModel
import com.itzcorpio.gifapplication.ui.theme.GifApplicationTheme

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: GifViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Manual DI for simplicity in this task
        val repository = GiphyRepositoryImpl()
        viewModel = GifViewModel(
            GetSelectedGifsUseCase(repository),
            SaveGifUseCase(repository)
        )

        Giphy.configure(this, "vKE44KJnA00cWlpCECMzlGPX1t76H3wV")

        enableEdgeToEdge()
        setContent {
            val selectedGifs by viewModel.selectedGifs.collectAsState()
            GifApplicationTheme {
                GifListScreen(
                    selectedGifs = selectedGifs,
                    onShowGiphyDialog = {
                        showGiphyDialog()
                    }
                )
            }
        }
    }

    private fun showGiphyDialog() {
        val giphyDialogFragment = GiphyDialogFragment.newInstance()
        giphyDialogFragment.gifSelectionListener = object : GiphyDialogFragment.GifSelectionListener {
            override fun onGifSelected(
                media: com.giphy.sdk.core.models.Media,
                searchTerm: String?,
                selectedContentType: GPHContentType
            ) {
                val url = media.images.fixedHeight?.gifUrl ?: media.images.original?.gifUrl
                url?.let {
                    viewModel.onGifSelected(media.id, it, media.title ?: "")
                }
                giphyDialogFragment.dismiss()
            }

            override fun onDismissed(selectedContentType: GPHContentType) {
            }

            override fun didSearchTerm(term: String) {
            }
        }
        giphyDialogFragment.show(supportFragmentManager, "giphy_dialog")
    }
}

@Preview(showBackground = true)
@Composable
fun GifListScreenPreview() {
    GifApplicationTheme {
        GifListScreen(
            selectedGifs = emptyList(),
            onShowGiphyDialog = {}
        )
    }
}
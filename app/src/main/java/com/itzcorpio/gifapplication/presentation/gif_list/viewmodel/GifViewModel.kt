package com.itzcorpio.gifapplication.presentation.gif_list.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itzcorpio.gifapplication.domain.model.GifModel
import com.itzcorpio.gifapplication.domain.usecase.GetSelectedGifsUseCase
import com.itzcorpio.gifapplication.domain.usecase.SaveGifUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GifViewModel(
    private val getSelectedGifsUseCase: GetSelectedGifsUseCase,
    private val saveGifUseCase: SaveGifUseCase
) : ViewModel() {

    val selectedGifs: StateFlow<List<GifModel>> = getSelectedGifsUseCase()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onGifSelected(id: String, url: String, title: String) {
        viewModelScope.launch {
            saveGifUseCase(GifModel(id, url, title))
        }
    }
}

package com.itzcorpio.gifapplication.data.repository

import com.itzcorpio.gifapplication.domain.model.GifModel
import com.itzcorpio.gifapplication.domain.repository.GiphyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class GiphyRepositoryImpl : GiphyRepository {
    private val _selectedGifs = MutableStateFlow<List<GifModel>>(emptyList())
    
    override fun getSelectedGifs(): Flow<List<GifModel>> = _selectedGifs.asStateFlow()

    override suspend fun saveGif(gif: GifModel) {
        _selectedGifs.update { currentList ->
            currentList + gif
        }
    }
}

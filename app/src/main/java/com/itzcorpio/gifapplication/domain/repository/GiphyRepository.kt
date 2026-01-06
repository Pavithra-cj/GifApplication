package com.itzcorpio.gifapplication.domain.repository

import com.itzcorpio.gifapplication.domain.model.GifModel
import kotlinx.coroutines.flow.Flow

interface GiphyRepository {
    fun getSelectedGifs(): Flow<List<GifModel>>
    suspend fun saveGif(gif: GifModel)
}

package com.itzcorpio.gifapplication.domain.usecase

import com.itzcorpio.gifapplication.domain.model.GifModel
import com.itzcorpio.gifapplication.domain.repository.GiphyRepository
import kotlinx.coroutines.flow.Flow

class GetSelectedGifsUseCase(private val repository: GiphyRepository) {
    operator fun invoke(): Flow<List<GifModel>> = repository.getSelectedGifs()
}

class SaveGifUseCase(private val repository: GiphyRepository) {
    suspend operator fun invoke(gif: GifModel) = repository.saveGif(gif)
}

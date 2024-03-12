package com.example.comics.data.repository

import com.example.comics.data.model.ItemModel
import com.example.comics.data.service.ComicsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ComicsRepositoryImpl(private val service: ComicsService): ComicsRepository {
    override suspend fun getComics(): Flow<List<ItemModel>> = flow  {
        val result = service.getComics()
        emit(result)
    }
}
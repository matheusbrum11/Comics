package com.example.comics.data.repository

import com.example.comics.repository.ItemModel
import kotlinx.coroutines.flow.Flow

interface ComicsRepository {
    suspend fun getComics(): Flow<List<ItemModel>>
}
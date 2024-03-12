package com.example.comics.data.repository

import com.example.comics.data.model.ItemModel
import kotlinx.coroutines.flow.Flow

interface ComicsRepository {
    suspend fun getComics(): Flow<ItemModel>
}
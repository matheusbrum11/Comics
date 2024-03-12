package com.example.comics.data.service

import com.example.comics.data.model.ItemModel
import retrofit2.http.GET

interface ComicsService {
    @GET("comics")
    suspend fun getComics(): List<ItemModel>
}
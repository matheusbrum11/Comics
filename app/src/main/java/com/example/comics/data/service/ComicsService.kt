package com.example.comics.data.service

import com.example.comics.data.model.ItemModel
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ComicsService {
    @GET("comics")
    suspend fun getComics(@QueryMap params: Map<String, String>,): ItemModel
}
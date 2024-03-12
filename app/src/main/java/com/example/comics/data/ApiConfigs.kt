package com.example.comics.data

import com.example.comics.BuildConfig

fun getComicsQuery(): Map<String, String> =  mapOf(
     "apikey" to BuildConfig.API_KEY,
     "ts" to BuildConfig.ts,
     "hash" to BuildConfig.hash,
 )

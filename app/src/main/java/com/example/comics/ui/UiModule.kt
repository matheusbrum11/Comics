package com.example.comics.ui

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { ComicsViewModel(get()) }
}

val UiModule = listOf(viewModels)
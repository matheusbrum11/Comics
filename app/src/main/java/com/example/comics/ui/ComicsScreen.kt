package com.example.comics.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.comics.compose.ui.components.HomeTopBar
import com.comics.compose.ui.theme.ComicsTheme
import com.example.comics.R
import com.example.comics.data.model.ItemModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComicsScreen(viewModel: ComicsViewModel = koinViewModel()) {
    val comicsUiState = viewModel.comicsResponse.collectAsState()
    val state = rememberPullToRefreshState()
    if (state.isRefreshing) {
        LaunchedEffect(true) {
            viewModel.getComics()
        }
    }
    Scaffold(topBar = {
        HomeTopBar(title = stringResource(id = R.string.comics))
    }) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            when (comicsUiState.value) {
                is UiStateResponse.Loading -> {

                }

                is UiStateResponse.Error -> {

                }

                is UiStateResponse.Success -> {
                    val itemList = (comicsUiState.value as UiStateResponse.Success).result
                    Box(Modifier.nestedScroll(state.nestedScrollConnection)) {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            itemsIndexed(itemList) { _, d ->
                                ComicsItem(comicsInfo = d)
                            }
                        }
                        PullToRefreshContainer(
                            modifier = Modifier.align(Alignment.TopCenter),
                            state = state,
                        )
                    }
                }
            }

        }

    }
}

@Composable
fun ComicsItem(comicsInfo: ItemModel) {

}

@Preview
@Composable
fun ComicsScreenPreview() {
    ComicsTheme(darkTheme = false) {
        ComicsScreen()
    }
}

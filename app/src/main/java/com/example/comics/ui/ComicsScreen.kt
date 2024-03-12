package com.example.comics.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.comics.compose.ui.components.HomeTopBar
import com.comics.compose.ui.theme.ComicsTheme
import com.comics.compose.ui.theme.itemSubStyle
import com.comics.compose.ui.theme.itemTitleStyle
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
            state.endRefresh()
        }
    }
    Scaffold(topBar = {
        HomeTopBar(title = stringResource(id = R.string.comics))
    }) { paddingValues ->
        Box(
            Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .nestedScroll(state.nestedScrollConnection)
        ) {
            when (comicsUiState.value) {
                is UiStateResponse.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(50.dp)
                            .align(Alignment.Center)
                            .padding(bottom = 50.dp),
                        color = MaterialTheme.colorScheme.secondary,
                        trackColor = MaterialTheme.colorScheme.surfaceVariant,
                    )
                }

                is UiStateResponse.Error -> {
                    Box(
                        Modifier
                            .fillMaxSize()
                            .clickable(enabled = true) { viewModel.getComics() }) {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.Center)
                                .padding(bottom = 50.dp)
                                .padding(horizontal = 16.dp),
                            color = MaterialTheme.colorScheme.onBackground,
                            textAlign = TextAlign.Center,
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Black,
                                        textDecoration = TextDecoration.None,
                                        fontSize = 16.sp
                                    )
                                ) {
                                    append(stringResource(id = R.string.erro_comics))
                                }
                                withStyle(
                                    style = SpanStyle(
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold,
                                        textDecoration = TextDecoration.Underline,
                                        fontSize = 16.sp
                                    )
                                ) {
                                    append("Tentar Novamente")
                                }
                            },
                        )
                    }

                }

                is UiStateResponse.Success -> {
                    val itemList = (comicsUiState.value as UiStateResponse.Success).result

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        itemsIndexed(itemList) { _, d ->
                            ComicsItem(comicsInfo = d)
                        }
                    }

                }
            }
            PullToRefreshContainer(
                modifier = Modifier.align(Alignment.TopCenter),
                state = state,
            )
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ComicsItem(comicsInfo: UiItemModel, modifier: Modifier = Modifier) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onBackground),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Row(
            modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp)
        ) {
            Box(
                modifier = modifier
                    .size(50.dp)
                    .clip(CircleShape)
            ) {
                GlideImage(
                    model = comicsInfo.image,
                    contentDescription = stringResource(R.string.comics_photo),
                    modifier = Modifier,
                    contentScale = ContentScale.Crop
                )
            }
            Column(Modifier.padding(start = 8.dp)) {
                Text(text = comicsInfo.title, style = itemTitleStyle())
                Text(text = comicsInfo.subtitle, style = itemSubStyle())
            }
        }
    }
}

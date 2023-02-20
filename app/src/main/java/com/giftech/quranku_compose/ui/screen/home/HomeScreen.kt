package com.giftech.quranku_compose.ui.screen.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.giftech.quranku_compose.R
import com.giftech.quranku_compose.utils.Resource
import com.giftech.quranku_compose.data.model.LastRead
import com.giftech.quranku_compose.data.model.Surah
import com.giftech.quranku_compose.ui.components.BoxLastRead
import com.giftech.quranku_compose.ui.components.SurahItem
import com.giftech.quranku_compose.ui.components.TextTitle
import com.giftech.quranku_compose.ui.theme.QuranKuComposeTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onSurahClick: (Int) -> Unit = {},
    onLastReadClick: (Int) -> Unit = {},
) {
    val listSurah = remember {
        viewModel.listSurah
    }.collectAsState()
    val lastRead = remember {
        viewModel.lastRead
    }.collectAsState()

    LaunchedEffect(Unit){
        viewModel.getLastRead()
    }

    Scaffold {
        listSurah.value.let {
            when (it) {
                is Resource.Error -> {
                    Log.d("TAG", "HomeScreen: ${it.message}")
                }
                is Resource.Loading -> {
                    Log.d("TAG", "HomeScreen: Loading")
                }
                is Resource.Success -> {
                    if (it.data != null) {
                        HomeContent(
                            listSurah = it.data,
                            lastRead = lastRead.value,
                            onSurahClicked = onSurahClick,
                            onLastReadClick = {
                                onLastReadClick(lastRead.value.nomorSurah)
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    listSurah: List<Surah>,
    lastRead: LastRead,
    onSurahClicked: (Int) -> Unit,
    onLastReadClick: () -> Unit,
) {
    LazyColumn(
        Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            Column {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TextTitle(text = R.string.title)
                }
                Text(
                    text = stringResource(id = R.string.salam),
                    Modifier.padding(vertical = 16.dp),
                    fontSize = 18.sp
                )
                BoxLastRead(
                    surah = lastRead.namaSurah,
                    ayat = lastRead.nomorAyat,
                    onClick = onLastReadClick
                )
                TextTitle(
                    text = R.string.surah,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 8.dp)
                )
            }
        }
        items(listSurah) {
            SurahItem(
                surah = it,
                onSurahClicked = onSurahClicked
            )
        }
    }
}


@Preview
@Composable
fun HomePreview() {
    QuranKuComposeTheme() {
        HomeScreen {}
    }
}
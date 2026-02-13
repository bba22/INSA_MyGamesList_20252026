package com.insa.mygameslist

import android.R.attr.navigationIcon
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults.contentWindowInsets
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.insa.mygameslist.data.Game
import com.insa.mygameslist.data.IGDB
import com.insa.mygameslist.data.IGDB.covers
import com.insa.mygameslist.data.IGDB.games
import com.insa.mygameslist.functions.GameCell
import com.insa.mygameslist.ui.theme.MyGamesListTheme
import res.drawable.*



@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        IGDB.load(this)

        enableEdgeToEdge()
        setContent {

            MyGamesListTheme {
                Navigate()
            }
        }
    }
}

@SuppressLint("ResourceType")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Navigate() {
    val backStack = remember { mutableStateListOf<Any>(Screen.GameList)}

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Screen.GameList> {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = Color(0xFF9FDDE6),
                                titleContentColor = Color.Black,
                            ),
                            title = { Text("My Games List") }
                        )
                    },
                    contentWindowInsets = WindowInsets.systemBars,
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                        GameList(
                            innerPadding,
                            { gameId -> backStack.add(Screen.GameDetail(gameId))}
                        )
                    }
                }

            entry<Screen.GameDetail> { key ->
                val game = IGDB.games.find { it.id == key.id}
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = Color(0xFF9FDDE6),
                                titleContentColor = Color.Black,
                            ),
                            title = {
                                Text(
                                    game?.name ?: "Détails"
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { backStack.removeLastOrNull() }) {
                                    Icon(
                                        painter = painterResource(R.drawable.baseline_arrow_back_24),
                                        contentDescription = stringResource(R.drawable.baseline_arrow_back_24)
                                    )
                                }
                            },
                            )
                    },
                    contentWindowInsets = WindowInsets.systemBars,
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    GameDetails(
                        gameId = key.id,
                        innerPadding = innerPadding
                    )
                }
            }
        }
    )
}

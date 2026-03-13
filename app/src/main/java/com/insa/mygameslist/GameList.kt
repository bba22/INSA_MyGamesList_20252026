package com.insa.mygameslist

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.insa.mygameslist.data.IGDB
import com.insa.mygameslist.data.IGDB.covers
import com.insa.mygameslist.functions.GameCell

@Composable
fun GameList(innerPadding: PaddingValues, recherche:String, onGameClick: (Long)->Unit) {
    val filteredGames = remember(recherche) {   //recharge seulement quand recherche change
        if (recherche.isEmpty()) {
            IGDB.games  // tous les jeux si rech vide
        } else {
            IGDB.games.filter { game -> //garder seulement les jeux qui matchent
                // trie par nom
                val matchNom = game.name.contains(recherche, ignoreCase = true)
                // trie par genre
                val matchGenre = game.genres.any { genreId ->
                    IGDB.genres.find { it.id == genreId }?.name?.contains(
                        recherche,
                        ignoreCase = true
                    ) == true
                }
                // trie par platforme
                val matchPlatform = game.platforms.any { platformId ->
                    IGDB.platform.find { it.id == platformId }?.name?.contains(
                        recherche, ignoreCase = true
                    ) == true
                }
                matchNom || matchGenre || matchPlatform // match si au moins un critère est bon
            }
        }


        }
        if (filteredGames.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "No match :(",
                    fontSize = 24.sp,
                    color = Color.Gray
                )
            }
        }else{
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(filteredGames) { game ->
                    GameCell(
                        game.id,
                        game.name,
                        covers.find { it.id == game.cover }?.url,
                        game.genres,
                        innerPadding,
                        { onGameClick(game.id) }
                    )
                }

        }
            }
        /*


        }
         */

    }

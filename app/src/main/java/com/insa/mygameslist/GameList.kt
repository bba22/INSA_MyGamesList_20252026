package com.insa.mygameslist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.insa.mygameslist.data.IGDB
import com.insa.mygameslist.data.IGDB.covers
import com.insa.mygameslist.functions.GameCell

@Composable
fun GameList(innerPadding: PaddingValues, onGameClick: (Long)->Unit){
    LazyColumn(modifier=Modifier.padding(innerPadding)){
        items(IGDB.games){
                game -> GameCell(game.id, game.name, covers.find { it.id == game.cover }?.url, game.genres, innerPadding,  { onGameClick(game.id)} )
        }

    }

}
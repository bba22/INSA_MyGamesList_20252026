package com.insa.mygameslist.functions

import android.R.id.bold
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.insa.mygameslist.R
import com.insa.mygameslist.data.Game
import com.insa.mygameslist.data.IGDB
import com.insa.mygameslist.data.IGDB.games
import com.insa.mygameslist.data.IGDB.genres
import kotlinx.datetime.format.Padding


@Composable
fun GameCell(id: Long, nom: String?, URL : String?, Genres: List<Long>, pad: PaddingValues, onClick: () -> Unit = {} ){
    var afficherGenres : String = "Genres : "
    for (idGenre in Genres){
        afficherGenres += genres.find { it.id == idGenre}?.name
    }

    Row (
        modifier=Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(9.dp))
            .border(2.dp, Color.DarkGray, RoundedCornerShape(10.dp))
            .background(color = Color(0xFFF4DEFF) )
            .fillMaxWidth()
            .clickable(onClick = onClick)

    ) {
        Column {
            AsyncImage(
                model = "https:"+URL,
                contentDescription = null,
                modifier = Modifier.size(70.dp)
            )
        }
        Column {
            if (nom != null) {
                Text(nom, fontWeight = FontWeight.Bold)
            }
            Text(afficherGenres)
        }
    }

}




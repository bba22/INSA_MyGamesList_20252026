package com.insa.mygameslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.insa.mygameslist.data.IGDB
import com.insa.mygameslist.data.IGDB.covers
import com.insa.mygameslist.data.IGDB.games
import com.insa.mygameslist.data.IGDB.genres
@Composable
fun GameDetails(identifiant: Long, pad: PaddingValues) {
    // 1. récupérer le jeu avec l'ID donné
    val game1 = games.find { it.id == identifiant }

    // 2. Column avec scroll vertical
    Column( // Ligne du nom
        modifier = Modifier
            .padding(top = pad.calculateTopPadding())   // Évite que ça passe sous l'AppBar
            .fillMaxWidth() // Prend toute la largeur
            .background(Color.White)    // Fond blanc
            .verticalScroll(rememberScrollState())  // Permet de scroller
    ) {

        //NOM DU JEU
        Spacer(modifier = Modifier.height(25.dp))   // espace vide

        val isFavorite = Favorite.isFav(identifiant)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            game1?.name?.let {  // si le nom existe
                Text(
                    it, // nom du jeu
                    fontSize = 25.sp,   // taille du texte
                    fontWeight = FontWeight.Bold,   // gras
                    textDecoration = TextDecoration.Underline,  // Souligné
                    textAlign = TextAlign.Center ,   // centré
                    modifier = Modifier.weight(1f)

                )
            }

            IconButton(
                onClick = {
                    Favorite.basculerFav(identifiant)
                }
            ) {
                Icon(
                    painter = if(isFavorite){
                        painterResource(R.drawable.filled_star)
                    } else {
                        painterResource(R.drawable.outlined_star)
                    },
                    contentDescription = if (isFavorite) "Retirer des favoris" else "Ajouter aux favoris",
                    tint = if (isFavorite) Color(0xFFFFA500) else Color.Gray


                    )
            }
        }


        // COVER DU JEU
        Spacer(modifier = Modifier.height(20.dp))
        AsyncImage( // chargé depuis internet
            model = "https:" + covers.find { it.id == game1?.cover }?.url,
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp),

            contentDescription = null
        )

        // GENRE DU JEU
        Spacer(modifier = Modifier.height(8.dp))
        val genres1 = game1?.genres
        var stringGenres = ""
        if (genres1 != null) {
            for (idGenre in genres1) {  // Pour chaque ID de genre
                stringGenres += genres.find { it.id == idGenre }?.name  // trouve le nom
                stringGenres += ", "    // ajoute une virgule
            }
        }
        stringGenres=stringGenres.removeSuffix(", ")    // Enlève la dernière virgule

        Text(
            stringGenres,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            color = Color.Gray,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // LAZY ROW : LOGOS DES PLATEFORMES

        val platforms = mutableListOf<String>()
        val platforms_id1 = game1?.platforms    // Liste des IDs de plateformes
        val platforms_id2=mutableListOf<Long>()

        // Première boucle : récupère les IDs des logos
        for(plat1 in (platforms_id1!!)){
            platforms_id2.add(IGDB.platform.firstOrNull{it.id==plat1}?.platform_logo?:0)
        }
        // Deuxième boucle : récupère les URLs des logos
        for (plat2 in (platforms_id2)){
            val url = IGDB.platformLogos.firstOrNull{it.id==plat2}?.url
            if (url != null) {
                platforms.add(url)
            }
        }

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            items(platforms) { plat ->
                AsyncImage(
                    model = "https:$plat",
                    contentDescription = "Image depuis URL",
                    modifier = Modifier
                        .size(60.dp)
                )
            }
        }

        //DESCRIPTION DU JEU
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = game1?.summary ?: "",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),  // Marge sur les côtés
            textAlign = TextAlign.Justify,  // justifier le texte
            fontSize = 14.sp,
            lineHeight = 20.sp  // Espacement entre les lignes
        )
        Spacer(modifier = Modifier.height(40.dp)) //espace en bas
    }
}

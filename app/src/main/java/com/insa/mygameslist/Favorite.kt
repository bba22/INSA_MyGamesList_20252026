package com.insa.mygameslist

import androidx.compose.runtime.mutableStateListOf

object Favorite {
    // liste des ids des jeux favoris
    private val favs = mutableStateListOf<Long>()
    val favorites: List<Long> = favs

    // vérifie si un jeu est favori
    fun isFav(gameId: Long): Boolean{
        return favs.contains(gameId)
    }

    //ajouter/retirer un jeu des favoris
    fun basculerFav(gameId: Long){
        if (isFav(gameId)){
            favs.remove(gameId)     // retire des favoris
        } else {
            favs.add(gameId)        // ajoute aux favoris
        }
    }


}
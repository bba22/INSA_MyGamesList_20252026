package com.insa.mygameslist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import kotlinx.serialization.Serializable
@Serializable
sealed class Screen {
    @Serializable
    data object GameList : Screen()
    @Serializable
    data class GameDetail(val id : Long) : Screen() //passer l'id du jeu
}


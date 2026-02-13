package com.insa.mygameslist.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.insa.mygameslist.R

object IGDB {

    lateinit var covers: List<Cover>
    lateinit var games: List<Game>
    lateinit var genres: List<Genre>
    lateinit var platformLogos: List<PlatformLogo>
    lateinit var platform: List<Platform>

    fun load(context: Context) {
        val coversFromJson: List<Cover> = Gson().fromJson(
            context.resources.openRawResource(R.raw.covers).bufferedReader(),
            object : TypeToken<List<Cover>>() {}.type
        )
        val gamesFromJson: List<Game> = Gson().fromJson(
            context.resources.openRawResource(R.raw.games).bufferedReader(),
            object : TypeToken<List<Game>>() {}.type
        )
        val genresFromJson: List<Genre> = Gson().fromJson(
            context.resources.openRawResource(R.raw.genres).bufferedReader(),
            object : TypeToken<List<Genre>>() {}.type
        )
        val platformLogosFromJson: List<PlatformLogo> = Gson().fromJson(
            context.resources.openRawResource(R.raw.platform_logos).bufferedReader(),
            object : TypeToken<List<Cover>>() {}.type
        )
        val platformFromJson: List<Platform> = Gson().fromJson(
            context.resources.openRawResource(R.raw.platforms).bufferedReader(),
            object : TypeToken<List<Platform>>() {}.type
        )
        covers = coversFromJson
        games = gamesFromJson
        genres = genresFromJson
        platformLogos = platformLogosFromJson
        platform = platformFromJson
    }


}

data class Cover(val id: Long, val url: String)
data class Game(val id: Long, val cover: Long, val first_release_date: Long, val genres : List<Long>, val name: String, val platforms: List<Long>, val summary: String, val total_rating: Double)
data class Genre(val id: Long, val name: String)
data class PlatformLogo(val id: Long, val url: String)
data class Platform(val id: Long, val name: String, val platform_logo: Long)
package com.insa.mygameslist.data

import com.google.gson.annotations.SerializedName

data class ApiGame(
    val id: Long,
    val cover: Long?,
    // noms de l'API -> Kotlin
    @SerializedName("first_release_data") val firstReleaseDate: Long?,
    val genres: List<Long>?,
    val name: String,
    val platforms: List<Long>?,
    val summary: String?,
    @SerializedName("total_rating") val total_rating: Double?
)

data class ApiCover(
    val id: Long,
    val url: String
)

data class ApiGenre(
    val id: Long,
    val name: String
)

data class ApiPlatform(
    val id: Long,
    val name: String,
    @SerializedName("platform_logo") val platformLogo: Long?
)

data class ApiPlatformLogo(
    val id: Long,
    val url: String
)
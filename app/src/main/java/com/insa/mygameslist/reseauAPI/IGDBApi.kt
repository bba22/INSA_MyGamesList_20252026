package com.insa.mygameslist.reseauAPI

import com.insa.mygameslist.data.ApiCover
import com.insa.mygameslist.data.ApiGame
import com.insa.mygameslist.data.ApiGenre
import com.insa.mygameslist.data.ApiPlatform
import com.insa.mygameslist.data.ApiPlatformLogo
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface IGDBApi {

    @POST("games")  //type de requete HTTP
    @Headers(
        "Client-ID: bm3vo60llto3r4bq8lf8gc611nlo9u",
        "Authorization: Bearer oszk7zkjpdhfuzvfiv32434xt37hhc"
    )
    suspend fun getGames(@Body body: RequestBody): List<ApiGame>    //suspend = fonction asynchrone et body = corps de la requete

    @POST("cover")
    @Headers(
        "Client-ID: bm3vo60llto3r4bq8lf8gc611nlo9u",
        "Authorization: Bearer oszk7zkjpdhfuzvfiv32434xt37hhc"
    )
    suspend fun getCovers(@Body body: RequestBody): List<ApiCover>

    @POST("genre")
    @Headers(
        "Client-ID: bm3vo60llto3r4bq8lf8gc611nlo9u",
        "Authorization: Bearer oszk7zkjpdhfuzvfiv32434xt37hhc"
    )
    suspend fun getGenres(@Body body: RequestBody): List<ApiGenre>

    @POST("platform")
    @Headers(
        "Client-ID: bm3vo60llto3r4bq8lf8gc611nlo9u",
        "Authorization: Bearer oszk7zkjpdhfuzvfiv32434xt37hhc"
    )
    suspend fun getPlatforms(@Body body: RequestBody): List<ApiPlatform>

    @POST("platform_logo")
    @Headers(
        "Client-ID: bm3vo60llto3r4bq8lf8gc611nlo9u",
        "Authorization: Bearer oszk7zkjpdhfuzvfiv32434xt37hhc"
    )
    suspend fun getPlatformLogos(@Body body: RequestBody): List<ApiPlatformLogo>

}


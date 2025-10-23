package com.example.fragtest.data

import com.google.gson.annotations.SerializedName

data class MusicGroups(
    //@SerializedName("id") val id: Int,
    @SerializedName("music_group") val group: String,
    @SerializedName("place_year_founded") val founded: String,
    @SerializedName("musical_genre") val genre: String,
    @SerializedName("group_members") val members: String,
    @SerializedName("history") val history: String,
    @SerializedName("discography") val discography: List<Discography>,
    @SerializedName("image_url") val image: String?
)

data class Discography(
    @SerializedName("album_title") val title: String,
    @SerializedName("year_released") val released: String,
    @SerializedName("song_list") val songList: List<String>,
)

/*
data class Image (
    @SerializedName("image") val image: String
)
* */

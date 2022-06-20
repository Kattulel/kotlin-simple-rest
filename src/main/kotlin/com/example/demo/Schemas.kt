package com.example.demo

import org.bson.types.ObjectId
import java.util.*

class Schemas {

    data class Users(
        val name: String,
        val age: Int,
        val createdAt: Date,
        val updatedAt: Date)

    data class Musics(
        val name: String,
        val artist: String,
        val playlistIds: Array<String>,
        val createdAt: Date,
        val updatedAt: Date
    )

    data class Playlists(
        val name: String,
        val userId: String,
        val createdAt: Date,
        val updatedAt: Date
    )
}
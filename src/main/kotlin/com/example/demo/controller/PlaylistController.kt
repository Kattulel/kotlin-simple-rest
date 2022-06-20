package com.example.demo.controller

import com.example.demo.Schemas
import com.example.demo.service.MongoService
import org.litote.kmongo.find
import org.litote.kmongo.getCollection
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("playlist")
class PlaylistController(val mongoService: MongoService) {

    @GetMapping("{playlistId}")
    fun findByPlaylistId(@PathVariable playlistId: String): ResponseEntity<List<Schemas.Musics>> {
        val doc = mongoService.db().getCollection<Schemas.Musics>()
        val musics: List<Schemas.Musics> = doc.find("{'playlistIds': { \$in: [ObjectId('$playlistId')] } }").toList()
        return ResponseEntity.ok(musics)
    }

}
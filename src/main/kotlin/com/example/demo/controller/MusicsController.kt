package com.example.demo.controller

import com.example.demo.Schemas
import com.example.demo.service.MongoService
import org.litote.kmongo.find
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("musics")
class MusicsController(val mongoService: MongoService) {

    @GetMapping
    fun findAll(): ResponseEntity<List<Schemas.Musics>> {
        val doc = mongoService.db().getCollection<Schemas.Musics>()
        val musics: List<Schemas.Musics> = doc.find().toList()
        return ResponseEntity.ok(musics)
    }

    @GetMapping("{musicId}/playlists")
    fun playlistsByMusicId(@PathVariable musicId: String): ResponseEntity<List<Schemas.Playlists>> {
        val doc = mongoService.db().getCollection<Schemas.Musics>()
        val docPlaylist = mongoService.db().getCollection<Schemas.Playlists>()
        val music: Schemas.Musics? = doc.findOne("{'_id': ObjectId('${musicId}')}")
        val playlistIds = music?.playlistIds
        val listPlaylistIds = playlistIds?.joinToString(
            prefix = "[",
            separator = ",",
            postfix = "]",
            transform = { "ObjectId('$it')" }
        )
        val playlists: List<Schemas.Playlists> = docPlaylist.find("{'_id': { \$in: $listPlaylistIds } }").toList()
        return ResponseEntity.ok(playlists)
    }
}
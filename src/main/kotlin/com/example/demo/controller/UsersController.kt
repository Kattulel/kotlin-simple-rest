package com.example.demo.controller

import com.example.demo.Schemas
import com.example.demo.service.MongoService
import org.litote.kmongo.eq
import org.litote.kmongo.find
import org.litote.kmongo.getCollection
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("users")
class UsersController(val mongoService: MongoService) {

    @GetMapping
    fun findAll(): ResponseEntity<List<Schemas.Users>> {
        val doc = mongoService.db().getCollection<Schemas.Users>()
        val users: List<Schemas.Users> = doc.find().toList()
        return ResponseEntity.ok(users)
    }

    @GetMapping("{userId}/playlist")
    fun findAllPlaylists(@PathVariable userId: String): ResponseEntity<List<Schemas.Playlists>> {
        val doc = mongoService.db().getCollection<Schemas.Playlists>()
        val playlists: List<Schemas.Playlists> = doc.find("{'userId': ObjectId('${userId}')}").toList()
        return ResponseEntity.ok(playlists)
    }
}
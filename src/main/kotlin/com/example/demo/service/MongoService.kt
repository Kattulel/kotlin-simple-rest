package com.example.demo.service

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.springframework.stereotype.Service

@Service
class MongoService {
    private var client: MongoClient = KMongo.createClient("mongodb+srv://admin:admin@cluster0.n5ow2vn.mongodb.net/streaming?retryWrites=true&w=majority")
    private var database: MongoDatabase = client.getDatabase("streaming")

    fun db(): MongoDatabase {
        return this.database
    }
}
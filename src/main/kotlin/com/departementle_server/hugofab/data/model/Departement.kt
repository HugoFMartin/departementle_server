package com.departementle_server.hugofab.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Departement (
    val name: String,
    val img: String,
    val id: String,
    val guessed: Boolean,
    val lat: Double,
    val long: Double,
    @BsonId
    val _id: String
)
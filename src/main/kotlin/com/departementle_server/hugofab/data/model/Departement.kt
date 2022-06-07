package com.departementle_server.hugofab.data.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId

@Serializable
data class Departement (
    val name: String,
//    val svgShape: String,
    val geojson: String,
    val id: String,
    val guessed: Boolean,
    @BsonId
    val _id: String
)
package org.example.project.api

import kotlinx.serialization.Serializable

@Serializable
data class CharactersResponse(
    val results: List<Character>
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String = "",
    val gender: String,
    val origin: LocationRef,
    val location: LocationRef,
    val image: String,
    val episode: List<String>,
    val url: String,
    val created: String
)

@Serializable
data class LocationRef(
    val name: String,
    val url: String
)


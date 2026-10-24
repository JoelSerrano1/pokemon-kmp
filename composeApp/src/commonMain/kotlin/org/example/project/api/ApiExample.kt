package org.example.project.api

object ApiExample {

    private const val BASE_URL = "https://rickandmortyapi.com/api"

    suspend fun fetchCharacters(): CharactersResponse {
        val client = ApiClient(createHttpClient())
        return client.get("$BASE_URL/character")
    }

    suspend fun fetchCharacter(id: Int): Character {
        val client = ApiClient(createHttpClient())
        return client.get("$BASE_URL/character/$id")
    }
}
package org.example.project.storage

import org.example.project.api.Character

//una única instancia global para el personaje seleccionado !!! OBJECTS SINGLETONS !!!
object CharacterStore {
    var selectedCharacter: Character? = null
    var allCharacters: List<Character> = emptyList()
}
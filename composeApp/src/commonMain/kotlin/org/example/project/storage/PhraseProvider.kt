package org.example.project.storage

object PhraseProvider {
    private val phrases = listOf(
        "Wubba Lubba Dub Dub!",
        "Marranete...",
        "NICE!!!",
        "Pagas 2?",
        "Acaba con mi sufrimiento",
        "Viejas en bikini",
        "Pollo Pollo Pollo Pollo"
    )

    fun randomPhrase(): String {
        return phrases.random()
    }
}
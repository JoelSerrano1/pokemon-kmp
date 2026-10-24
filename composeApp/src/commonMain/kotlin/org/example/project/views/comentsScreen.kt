package org.example.project.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.storage.CharacterStore
import org.example.project.storage.CommentStore
import org.example.project.storage.PhraseProvider


class comentsScreen : Screen() {

    override val screenKey = "coments"

    @Composable
    override fun execute() {

        val commentedCharacters = CharacterStore.allCharacters.filter { character ->
            !CommentStore.comments[character.id].isNullOrBlank()
        }

        var randomPhrase by remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            randomPhrase = PhraseProvider.randomPhrase()
        }

        MaterialTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Button(
                    onClick = { Navigator.navigateTo("home") }
                ) {
                    Text("pa atra")
                }
                Text("Total personajes: ${CharacterStore.allCharacters.size}")
                Text("Total comentarios: ${CommentStore.comments.size}")
                Text(
                    text = "Mi colección personal",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(randomPhrase,
                    modifier = Modifier.padding(bottom = 12.dp),
                    fontFamily = FontFamily.Cursive,
                    fontSize = 30.sp)

                if (commentedCharacters.isEmpty()) {
                    Text("Todavía no has añadido comentarios.")
                } else {
                    LazyColumn {
                        items(commentedCharacters) { character ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp)
                                    .clickable {
                                        CharacterStore.selectedCharacter = character
                                        Navigator.navigateTo("detail")
                                    }
                            ) {
                                ImageFromUrl(
                                    url = character.image,
                                    description = character.name
                                )

                                Column(modifier = Modifier.padding(12.dp)) {
                                    Text(
                                        text = character.name,
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Text("Especie: ${character.species}")
                                    Text("Comentario: ${CommentStore.comments[character.id]}")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
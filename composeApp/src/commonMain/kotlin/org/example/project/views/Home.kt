package org.example.project.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.example.project.api.ApiExample
import org.example.project.api.Character
import org.example.project.storage.CharacterStore
import androidx.compose.material3.Button
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import org.example.project.storage.PhraseProvider



class HomeScreen : Screen() {
    override val screenKey = "home"

    @Composable
    override fun execute() {
        var characters by remember { mutableStateOf<List<Character>>(emptyList()) }
        var loading by remember { mutableStateOf(true) }
        var error by remember { mutableStateOf<String?>(null) }
        var randomPhrase by remember { mutableStateOf("") }

        LaunchedEffect(Unit) {
            try {
                characters = ApiExample.fetchCharacters().results
                CharacterStore.allCharacters = characters
                randomPhrase = PhraseProvider.randomPhrase()
            } catch (e: Exception) {
                error = e.message
            }
            loading = false
        }

        MaterialTheme {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Button(
                    onClick = { Navigator.navigateTo("coments") }
                ) {
                    Text("Ver colección personal")
                }

                Text(
                    text = "Lista de personajes",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 16.dp),
                )

                when {
                    loading -> {
                        CircularProgressIndicator()
                    }

                    error != null -> {
                        Text(
                            text = "Error: $error",
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    else -> {
                        Text(
                            text = randomPhrase,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(bottom = 16.dp),
                            fontFamily = FontFamily.Cursive,
                            fontSize = 30.sp
                        )

                        LazyColumn {
                            items(characters) { character ->
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
                                        Text("Género: ${character.gender}")
                                        Text("Estado: ${character.status}")
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
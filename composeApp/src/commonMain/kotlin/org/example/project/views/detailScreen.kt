package org.example.project.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.storage.CharacterStore
import org.example.project.storage.CommentStore
import org.example.project.storage.PhraseProvider


class DetailScreen : Screen() {
    override val screenKey = "detail" // id o nombre para llamar a esta pantalla

    @Composable
    override fun execute() {

        val character = CharacterStore.selectedCharacter

        var randomPhrase by remember { mutableStateOf("") }

        if (character == null) {
            Text("No hay personaje seleccionado")
            return
        }

        // se carga el comentario
        var comment by remember {
            mutableStateOf(CommentStore.comments[character.id] ?: "")
        }

        LaunchedEffect(Unit) {
            randomPhrase = PhraseProvider.randomPhrase()
        }

        MaterialTheme {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .fillMaxSize()
                    .padding(16.dp)
            ) {

                Text(randomPhrase,
                    modifier = Modifier.padding(bottom = 12.dp),
                    fontFamily = FontFamily.Cursive,
                    fontSize = 30.sp)

                ImageFromUrl(
                    url = character.image,
                    description = character.name
                )

                Text(character.name, style = MaterialTheme.typography.headlineMedium)
                Text("Especie: ${character.species}")
                Text("Género: ${character.gender}")
                Text("Estado: ${character.status}")
                Text("Origen: ${character.origin.name}")
                Text("Ubicación actual: ${character.location.name}")

                OutlinedTextField(
                    value = comment,
                    onValueChange = { comment = it },
                    label = { Text("Comentario personal") },
                    modifier = Modifier.padding(top = 16.dp)
                )

                Button(
                    onClick = {
                        CommentStore.comments[character.id] = comment
                    },
                    modifier = Modifier.padding(top = 12.dp)
                ) {
                    Text("Guardar comentario")
                }

                Button(
                    onClick = { Navigator.navigateTo("home") },
                    modifier = Modifier.padding(top = 12.dp)
                ) {
                    Text("Volver")
                }
            }
        }
    }
}
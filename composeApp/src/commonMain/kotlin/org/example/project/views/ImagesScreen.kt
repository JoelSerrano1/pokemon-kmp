package org.example.project.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage

class ImagesScreen : Screen() {
    override val screenKey = "images"

    @Composable
    override fun execute() {
        MaterialTheme {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                Button(
                    onClick = { Navigator.navigateTo("home") },
                    modifier = Modifier.padding(bottom = 16.dp),
                ) {
                    Text("Volver a Home")
                }
                Text(
                    text = "Imágenes desde URL",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 16.dp),
                )
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    items(IMAGE_URLS) { item ->
                        ImageFromUrl(
                            url = item.url,
                            description = item.description,
                        )
                    }
                }
            }
        }
    }
}

private data class ImageItem(val url: String, val description: String)

private val IMAGE_URLS = listOf(
    ImageItem("https://picsum.photos/id/1/400/400", "Naturaleza"),
    ImageItem("https://picsum.photos/id/10/400/400", "Bosque"),
    ImageItem("https://picsum.photos/id/100/400/400", "Mar"),
    ImageItem("https://picsum.photos/id/1000/400/400", "Montaña"),
    ImageItem("https://picsum.photos/id/1001/400/400", "Ciudad"),
    ImageItem("https://picsum.photos/id/1002/400/400", "Atardecer"),
)

@Composable
 fun ImageFromUrl(
    url: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SubcomposeAsyncImage(
            model = url,
            contentDescription = description,
            modifier = Modifier
                .fillMaxWidth()
                .size(160.dp),
            contentScale = ContentScale.Fit,
            loading = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "…",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            },
            error = {
                Text(
                    text = "Error",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                )
            },
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(top = 4.dp),
        )
    }
}

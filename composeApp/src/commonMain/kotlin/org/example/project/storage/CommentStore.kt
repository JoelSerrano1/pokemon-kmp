package org.example.project.storage


//Para guardar los comentarios con la ID del PJ !! SINGLETON !!
object CommentStore {
    val comments = mutableMapOf<Int, String>()
}
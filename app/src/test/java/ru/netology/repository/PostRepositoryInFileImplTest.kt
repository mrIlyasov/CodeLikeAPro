package ru.netology.repository

import org.junit.Test

import org.junit.Assert.*

class PostRepositoryInFileImplTest(private val context: android.content.Context) {

    @Test
    fun addNextId() {
        var filename = "nextIdFile"
        var nextId = 1
        val file = context.filesDir.resolve(filename)
        context.openFileOutput(filename, android.content.Context.MODE_PRIVATE).bufferedWriter()
            .use {
                it.write(nextId)
            }
        var result = addNextId()
        assertEquals(2, result)
    }
}

package com.example.data.feature.common.source

import com.example.domain.feature.notes.model.Note
import cz.eman.kaal.domain.result.Result

interface NotesRemoteDataSource {

    suspend fun getNotes(): Result<List<Note>>

    suspend fun getNote(noteId: Int): Result<Note>

    suspend fun addNote(title: String): Result<Note>

    suspend fun updateNote(noteId: Int, title: String?): Result<Note>

    suspend fun deleteNote(noteId: Int): Result<Unit>
}
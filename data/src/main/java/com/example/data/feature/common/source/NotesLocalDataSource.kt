package com.example.data.feature.common.source

import com.example.domain.feature.notes.model.Note
import cz.eman.kaal.domain.result.Result

interface NotesLocalDataSource {

    suspend fun getNotes(): Result<List<Note>>

    suspend fun getNote(noteId: Int): Result<Note>

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(noteId: Int)
}
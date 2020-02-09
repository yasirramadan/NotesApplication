package com.example.domain.feature.notes.repository

import com.example.domain.feature.notes.model.Note

import cz.eman.kaal.domain.result.Result

interface NotesRepository {

    suspend fun getNotes(): Result<List<Note>>

    suspend fun getNote(noteId: Int): Result<Note>

    suspend fun addNote(title: String): Result<Unit>

    suspend fun updateNote(noteId: Int?, title: String?): Result<Unit>

    suspend fun deleteNote(noteId: Int): Result<Unit>
}
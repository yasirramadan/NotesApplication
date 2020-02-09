package com.example.infrastructure.feature.notes.datasource

import com.example.data.feature.common.source.NotesLocalDataSource
import com.example.domain.feature.notes.model.Note
import com.example.infrastructure.feature.notes.database.NoteEntity
import com.example.infrastructure.feature.notes.database.NotesDao
import com.example.infrastructure.feature.notes.mapper.NotesEntityMapper
import cz.eman.kaal.domain.result.ErrorResult
import cz.eman.kaal.domain.result.Result

class NotesLocalDataSourceImpl(private val notesDao: NotesDao) : NotesLocalDataSource {

    override suspend fun getNotes(): Result<List<Note>> {
        return try {
            val result = notesDao.getNotes()
            Result.success(NotesEntityMapper.mapFrom(result))
        } catch (e: Exception) {
            Result.error(ErrorResult())
        }
    }

    override suspend fun addNote(note: Note) {
        notesDao.addNote(NoteEntity(id = note.id, title = note.title))
    }

    override suspend fun updateNote(note: Note) {
        notesDao.updateNote(id = note.id, title = note.title)
    }

    override suspend fun deleteNote(noteId: Int) {
        notesDao.deleteNote(noteId)
    }

    override suspend fun getNote(noteId: Int): Result<Note> {
        TODO("not implemented")
    }
}
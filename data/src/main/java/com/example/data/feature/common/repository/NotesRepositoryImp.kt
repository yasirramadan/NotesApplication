package com.example.data.feature.common.repository

import com.example.domain.feature.notes.repository.NotesRepository
import com.example.data.feature.common.source.NotesRemoteDataSource
import com.example.data.feature.common.source.NotesLocalDataSource
import com.example.domain.feature.notes.model.Note
import cz.eman.kaal.domain.result.ErrorResult

import cz.eman.kaal.domain.result.Result

class NotesRepositoryImp(
    private val notesRemoteDataSource: NotesRemoteDataSource,
    private val notesLocalDataSource: NotesLocalDataSource
) : NotesRepository {

    override suspend fun getNotes(): Result<List<Note>> {
        val result = notesRemoteDataSource.getNotes()

        if (result.isSuccess()) {
            val notesList = result.getOrNull()

            if (!notesList.isNullOrEmpty()) {
                notesList.forEach {
                    notesLocalDataSource.addNote(note = it)
                }

                return notesLocalDataSource.getNotes()
            }

        }

        return notesLocalDataSource.getNotes()
    }

    override suspend fun addNote(title: String): Result<Unit> {
        return when (val result = notesRemoteDataSource.addNote(title)) {
            is Result.Success -> {
                notesLocalDataSource.addNote(Note(title = title))
                Result.success(Unit)
            }

            is Result.Error -> {
                Result.error(result.error)
            }

            else -> {
                Result.error(ErrorResult())
            }
        }
    }

    override suspend fun updateNote(noteId: Int?, title: String?): Result<Unit> {
        return when (val result =
            noteId?.let { notesRemoteDataSource.updateNote(noteId = it, title = title) }) {
            is Result.Success -> {
                notesLocalDataSource.updateNote(Note(id = noteId, title = title))
                Result.success(Unit)
            }

            is Result.Error -> {
                Result.error(error = result.error)
            }
            else -> {
                Result.error(error = ErrorResult())
            }
        }
    }

    override suspend fun deleteNote(noteId: Int): Result<Unit> {

        return when (val result = notesRemoteDataSource.deleteNote(noteId = noteId)) {
            is Result.Success -> {
                notesLocalDataSource.deleteNote(noteId = noteId)
                Result.success(Unit)
            }

            is Result.Error -> {
                Result.error(error = result.error)
            }

            else -> {
                Result.error(error = ErrorResult())
            }
        }
    }

    override suspend fun getNote(noteId: Int): Result<Note> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
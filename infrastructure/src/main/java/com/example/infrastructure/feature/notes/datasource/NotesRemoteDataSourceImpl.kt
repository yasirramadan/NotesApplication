package com.example.infrastructure.feature.notes.datasource

import com.example.data.feature.common.source.NotesRemoteDataSource
import com.example.domain.feature.notes.model.Note
import com.example.infrastructure.feature.notes.apiservice.NotesApiService
import com.example.infrastructure.feature.notes.mapper.NotesDtoMapper
import com.example.infrastructure.feature.notes.model.AddNoteRequest
import com.example.infrastructure.feature.notes.model.UpdateNoteRequest
import cz.eman.kaal.domain.result.*
import cz.eman.logger.logError

class NotesRemoteDataSourceImpl(private val notesApiService: NotesApiService) :
    NotesRemoteDataSource {

    override suspend fun getNotes(): Result<List<Note>> = callSafe(
        call = { fetchNotes() },
        errorMessage = "Cannot fetch notes there is problem connection to server try again"
    )

    override suspend fun addNote(title: String): Result<Note> = callSafe(
        call = { callAddNote(title) },
        errorMessage = "Cannot add note there is problem connection to server try again"
    )

    override suspend fun updateNote(noteId: Int, title: String?) = callSafe(
        call = { callUpdate(noteId, title) },
        errorMessage = "Cannot update note try again there is problem connection to server try again"
    )

    override suspend fun deleteNote(noteId: Int) = callSafe(
        call = { callDelete(noteId) },
        errorMessage = "Cannot delete note there is problem connection to server try again"
    )

    override suspend fun getNote(noteId: Int): Result<Note> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    /**
     * fetches all notes from server.
     */
    private suspend fun fetchNotes(): Result<List<Note>> {
        val response = notesApiService.getNotesAsync().await()

        return if (response.isSuccessful) {
            val body = response.body()

            body?.let { noteDto ->
                Result.Success(NotesDtoMapper.mapFrom(noteDto))
            } ?: run {
                Result.Error(
                    ErrorResult(
                        code = errorCode(response.code()),
                        message = response.message()
                    ), null
                )
            }
        } else {
            val errorResult =
                ApiErrorResult(code = errorCode(response.code()), message = response.message())

            logError { "Cannot fetch [$errorResult]" }

            Result.Error(errorResult)
        }
    }

    /**
     * adds a note
     *
     * @param title note title
     */
    private suspend fun callAddNote(title: String): Result<Note> {
        val response = notesApiService.addNoteAsync(AddNoteRequest(title)).await()

        return if (response.isSuccessful) {
            val body = response.body()

            body?.let {
                Result.Success(Note(id = it.id, title = it.title))
            } ?: run {
                Result.Error(
                    ErrorResult(
                        code = errorCode(response.code()),
                        message = response.message()
                    ), null
                )
            }
        } else {
            val errorResult =
                ApiErrorResult(code = errorCode(response.code()), message = response.message())
            logError { "Cannot fetch [$errorResult]" }
            Result.Error(errorResult)
        }
    }

    /**
     * updates note
     *
     * @param noteId
     * @param title
     */
    private suspend fun callUpdate(noteId: Int, title: String?): Result<Note> {
        val response =
            notesApiService.updateNoteAsync(noteId, UpdateNoteRequest(title = title)).await()

        return if (response.isSuccessful) {
            val body = response.body()

            body?.let {
                Result.Success(Note(id = it.id, title = it.title))
            } ?: run {
                Result.Error(
                    ErrorResult(
                        code = errorCode(response.code()),
                        message = response.message()
                    ), null
                )
            }
        } else {
            val errorResult =
                ApiErrorResult(code = errorCode(response.code()), message = response.message())

            logError { "Cannot fetch [$errorResult]" }

            Result.Error(errorResult)
        }
    }

    /**
     * deletes a note
     *
     * @param noteId
     */
    private suspend fun callDelete(noteId: Int): Result<Unit> {
        val response =
            notesApiService.deleteNoteAsync(noteId).await()

        return if (response.isSuccessful) {
            return Result.success(Unit)
        } else {
            val errorResult =
                ApiErrorResult(code = errorCode(response.code()), message = response.message())

            logError { "Cannot fetch [$errorResult]" }

            Result.Error(errorResult)
        }

    }
}
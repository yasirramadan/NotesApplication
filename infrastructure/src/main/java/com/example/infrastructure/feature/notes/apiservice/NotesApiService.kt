package com.example.infrastructure.feature.notes.apiservice

import com.example.infrastructure.feature.notes.model.AddNoteRequest
import com.example.infrastructure.feature.notes.model.NoteDto
import com.example.infrastructure.feature.notes.model.UpdateNoteRequest
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface NotesApiService {

    /**
     * gets all notes
     */
    @GET("/notes")
    fun getNotesAsync(): Deferred<Response<List<NoteDto>>>

    /**
     * gets a note
     */
    @GET("/notes/{id}")
    fun getNote(@Path("id") noteId: Long): Deferred<Response<NoteDto>>

    /**
     * adds a note
     */
    @POST("/notes")
    fun addNoteAsync(@Body request: AddNoteRequest): Deferred<Response<NoteDto>>

    /**
     * updates a note
     */
    @PUT("/notes/{id}")
    fun updateNoteAsync(@Path("id") noteId: Int,  @Body request: UpdateNoteRequest): Deferred<Response<NoteDto>>

    /**
     * deletes a note
     */
    @DELETE("/notes/{id}")
    fun deleteNoteAsync(@Path("id") noteId: Int): Deferred<Response<Unit>>

}
package com.example.infrastructure.feature.notes.database

import androidx.room.*

@Dao
interface NotesDao {

    @Query(value = "SELECT * FROM noteEntity")
    suspend fun getNotes(): List<NoteEntity>


    @Query("SELECT * from noteEntity WHERE id = :noteId")
    suspend fun getNote(noteId: Int): NoteEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(noteEntity: NoteEntity): Long

    @Query("UPDATE noteEntity SET title = :title WHERE id = :id")
    suspend fun updateNote(id: Int?, title: String?): Int

    @Query("DELETE FROM noteEntity WHERE id = :noteId")
    suspend fun deleteNote(noteId: Int)
}
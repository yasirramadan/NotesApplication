package com.example.infrastructure.feature.notes.mapper

import com.example.domain.feature.notes.model.Note
import com.example.infrastructure.feature.notes.database.NoteEntity
import cz.eman.kaal.domain.mapper.Mapper

object NotesEntityMapper :  Mapper<List<NoteEntity>, List<Note>>() {
    override fun mapFrom(from: List<NoteEntity>): List<Note> {
        return from.map {
            Note(id = it.id, title = it.title)
        }
    }
}
package com.example.infrastructure.feature.notes.mapper

import com.example.domain.feature.notes.model.Note
import com.example.infrastructure.feature.notes.model.NoteDto
import cz.eman.kaal.domain.mapper.Mapper

object NotesDtoMapper : Mapper<List<NoteDto>, List<Note>>() {
    override fun mapFrom(from: List<NoteDto>): List<Note> {
        return from.map {
            Note(id = it.id, title = it.title)
        }
    }
}
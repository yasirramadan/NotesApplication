package com.example.notes.presintation.feature.notes

import com.example.domain.feature.notes.model.Note
import com.example.notes.presintation.feature.notes.vo.NoteVO
import cz.eman.kaal.domain.mapper.Mapper

object NotesVOMapper : Mapper<List<Note>, List<NoteVO>>() {
    override fun mapFrom(from: List<Note>): List<NoteVO> {
        return from.map {
            NoteVO(id = it.id, title = it.title)
        }
    }
}
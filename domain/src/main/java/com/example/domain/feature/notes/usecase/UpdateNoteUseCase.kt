package com.example.domain.feature.notes.usecase

import com.example.domain.feature.notes.model.Note
import com.example.domain.feature.notes.repository.NotesRepository
import cz.eman.kaal.domain.result.Result
import cz.eman.kaal.domain.usecases.UseCaseResult

class UpdateNoteUseCase(private val notesRepository: NotesRepository)  : UseCaseResult<Unit, Note>() {

    override suspend fun doWork(params: Note): Result<Unit> = notesRepository.updateNote(noteId = params.id, title = params.title)
}
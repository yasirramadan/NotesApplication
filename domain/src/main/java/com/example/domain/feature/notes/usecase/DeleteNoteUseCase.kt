package com.example.domain.feature.notes.usecase

import com.example.domain.feature.notes.repository.NotesRepository
import cz.eman.kaal.domain.result.Result
import cz.eman.kaal.domain.usecases.UseCaseResult

class DeleteNoteUseCase(private val notesRepository: NotesRepository)  : UseCaseResult<Unit, Int>() {

    override suspend fun doWork(params: Int): Result<Unit> = notesRepository.deleteNote(noteId = params)
}
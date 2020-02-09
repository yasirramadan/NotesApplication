package com.example.domain.feature.notes.usecase

import com.example.domain.feature.notes.repository.NotesRepository
import com.example.domain.feature.notes.model.Note
import cz.eman.kaal.domain.result.Result
import cz.eman.kaal.domain.usecases.UseCaseResult

/**
 * By this use-case you can get a list of notes in an asynchronous way.
 *
 * @see[UseCaseResult]
 */
class GetNotesUseCase(private val notesRepository: NotesRepository) : UseCaseResult<List<Note>, Unit>() {
    override suspend fun doWork(params: Unit): Result<List<Note>> {
        return notesRepository.getNotes()
    }
}
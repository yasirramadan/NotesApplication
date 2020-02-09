package com.example.notes.presintation.feature.notes.state

import com.example.notes.presintation.feature.notes.vo.NoteVO

/**
 * represents states of Notes view.
 */
sealed class NotesViewState {

    object Loading : NotesViewState()

    object NoteAdded : NotesViewState()

    object NoteUpdated: NotesViewState()

    object NoteDeleted: NotesViewState()

    class Loaded(val notes: List<NoteVO>): NotesViewState()

    class Error(val error: String?, val notes: List<NoteVO>?) : NotesViewState()

}
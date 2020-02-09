package com.example.notes.presintation.feature.notes.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.feature.notes.model.Note
import com.example.domain.feature.notes.usecase.AddNoteUseCase
import com.example.domain.feature.notes.usecase.DeleteNoteUseCase
import com.example.domain.feature.notes.usecase.GetNotesUseCase
import com.example.domain.feature.notes.usecase.UpdateNoteUseCase
import com.example.notes.presintation.feature.notes.NotesVOMapper
import com.example.notes.presintation.feature.notes.state.NotesViewState
import com.example.notes.presintation.feature.notes.vo.NoteVO
import cz.eman.kaal.domain.result.Result
import cz.eman.kaal.presentation.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import timber.log.Timber


class NotesViewModel(
    private val getNotesUseCase: GetNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : BaseViewModel() {

    /**
     * represents different states of notes fragment
     */
    val viewState: MutableLiveData<NotesViewState> = MutableLiveData()

    /**
     * list of fetched notes.
     */
    private var noteList: List<NoteVO>? = null

    val selectedNote = MutableLiveData<NoteVO>()

    /**
     * Gets all notes
     */
    private fun getAllNotes(showLoading: Boolean) {
        if (showLoading) {
            viewState.value = NotesViewState.Loading
        }

        viewModelScope.launch {
            when (val result = getNotesUseCase.invoke(Unit)) {
                is Result.Success -> {

                    noteList = NotesVOMapper.mapFrom(result.data)

                    noteList?.let {
                        viewState.value = NotesViewState.Loaded(it)
                    }
                }

                is Result.Error -> {
                    viewState.value = NotesViewState.Error(result.error.message, noteList)

                    Timber.e(result.error.throwable)
                }
            }
        }
    }

    /**
     * Add a note
     */
    fun addNote(title: String) {
        viewState.value = NotesViewState.Loading

        viewModelScope.launch {
            when (val result = addNoteUseCase.invoke(title)) {
                is Result.Success -> {
                    viewState.value = NotesViewState.NoteAdded
                    reload()
                }

                is Result.Error -> {
                    viewState.value = NotesViewState.Error(result.error.message, noteList)

                    Timber.e(result.error.throwable)
                }
            }
        }
    }

    /**
     * update a note
     *
     * @param title updated note text.
     */
    fun updateNote(title: String?) {
        viewState.value = NotesViewState.Loading

        val note = selectedNote.value
        note?.title = title

        viewModelScope.launch {
            when (val result = updateNoteUseCase.invoke(Note(id = note!!.id, title = note.title))) {
                is Result.Success -> {
                    viewState.value = NotesViewState.NoteUpdated

                    reload()
                }

                is Result.Error -> {
                    viewState.value = NotesViewState.Error(result.error.message, noteList)

                    Timber.e(result.error.throwable)
                }
            }
        }
    }

    /**
     * delete a note
     */
    fun deleteNote() {
        val noteId = selectedNote.value?.id
        noteId?.let {

            viewModelScope.launch {
                when (val result = deleteNoteUseCase.invoke(it)) {
                    is Result.Success -> {
                        viewState.value = NotesViewState.NoteDeleted

                        reload()
                    }

                    is Result.Error -> {
                        viewState.value = NotesViewState.Error(result.error.message, noteList)

                        Timber.e(result.error.throwable)
                    }
                }
            }

        }
    }

    fun reload() {
        getAllNotes(false)
    }

    fun load() {
        getAllNotes(true)
    }

    fun setSelectedNote(note: NoteVO) {
        selectedNote.value = note
    }

    fun hasData(): Boolean = !noteList.isNullOrEmpty()
}
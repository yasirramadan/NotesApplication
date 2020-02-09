package com.example.notes.presintation.feature.notes.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.domain.feature.notes.model.Note
import com.example.domain.feature.notes.usecase.AddNoteUseCase
import com.example.domain.feature.notes.usecase.DeleteNoteUseCase
import com.example.domain.feature.notes.usecase.GetNotesUseCase
import com.example.domain.feature.notes.usecase.UpdateNoteUseCase
import com.example.notes.TestCoroutineRule
import com.example.notes.presintation.feature.notes.state.NotesViewState
import com.example.notes.presintation.feature.notes.viewmodel.NotesViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import cz.eman.kaal.domain.result.ErrorResult
import cz.eman.kaal.domain.result.Result
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentCaptor


@RunWith(JUnit4::class)
class NotesViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var testCoroutineRule = TestCoroutineRule()


    private val getNotesUseCase = mock<GetNotesUseCase>()

    private val addNotesUseCase = mock<AddNoteUseCase>()

    private val updateNotesUseCase = mock<UpdateNoteUseCase>()

    private val deleteNotesUseCase = mock<DeleteNoteUseCase>()

    private var observerState = mock<Observer<NotesViewState>>()

    private lateinit var viewModel: NotesViewModel


    @Before
    fun setup() {

        viewModel = NotesViewModel(
            getNotesUseCase = getNotesUseCase,
            addNoteUseCase = addNotesUseCase,
            updateNoteUseCase = updateNotesUseCase,
            deleteNoteUseCase = deleteNotesUseCase
        )
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetNotesSuccess() = testCoroutineRule.runBlockingTest {

        val result = listOf(Note(1, "test"))

        whenever(getNotesUseCase.invoke(Unit)).thenReturn(Result.success(result))

        viewModel.viewState.observeForever(observerState)
        viewModel.load()

        val argumentCaptor = ArgumentCaptor.forClass(NotesViewState::class.java)

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            val (loadingState, loadedState) = allValues
            assertTrue(loadingState is NotesViewState.Loading)
            assertTrue(loadedState is NotesViewState.Loaded)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetNotesReturnsError() = testCoroutineRule.runBlockingTest {

        val result = ErrorResult(message = "failed")

        whenever(getNotesUseCase.invoke(Unit)).thenReturn(Result.error(result))

        viewModel.viewState.observeForever(observerState)
        viewModel.load()


        val argumentCaptor = ArgumentCaptor.forClass(NotesViewState::class.java)

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            val (loadingState, errorState) = allValues
            assertTrue(loadingState is NotesViewState.Loading)
            assertTrue(errorState is NotesViewState.Error)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testAddNoteSuccess() = testCoroutineRule.runBlockingTest {

        whenever(addNotesUseCase.invoke("a note")).thenReturn(Result.success(Unit))

        viewModel.viewState.observeForever(observerState)
        viewModel.addNote("a note")

        val argumentCaptor = ArgumentCaptor.forClass(NotesViewState::class.java)

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            val (loadingState, noteAddedState) = allValues
            assertTrue(loadingState is NotesViewState.Loading)
            assertTrue(noteAddedState is NotesViewState.NoteAdded)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testAddNoteFailed() = testCoroutineRule.runBlockingTest {

        whenever(addNotesUseCase.invoke("a note")).thenReturn(Result.error(ErrorResult()))

        viewModel.viewState.observeForever(observerState)
        viewModel.addNote("a note")

        val argumentCaptor = ArgumentCaptor.forClass(NotesViewState::class.java)

        argumentCaptor.run {
            verify(observerState, times(2)).onChanged(capture())
            val (loadingState, errorState) = allValues
            assertTrue(loadingState is NotesViewState.Loading)
            assertTrue(errorState is NotesViewState.Error)
        }
    }
}
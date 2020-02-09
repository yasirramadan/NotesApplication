package example.domain.feature.notes.useCase

import com.example.domain.feature.notes.model.Note
import com.example.domain.feature.notes.repository.NotesRepository
import com.example.domain.feature.notes.usecase.GetNotesUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import cz.eman.kaal.domain.result.Result
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetNotesUseCaseTest {

    private val notesRepository: NotesRepository = mock()

    lateinit var getNotesUseCase: GetNotesUseCase

    @Before
    fun setUp() {
        this.getNotesUseCase = GetNotesUseCase(notesRepository)
    }

    @Test
    fun getNotesSuccess() = runBlocking {
        whenever(notesRepository.getNotes()).thenReturn( Result.success(listOf(Note(1, "test1"), Note(2, "test2"))))

        val result = getNotesUseCase.invoke(Unit)
        assertNotNull(result)

        val  list = result.getOrNull()
        assertNotNull(list)

        assertEquals(1, list?.get(0)?.id)
        assertEquals("test1", list?.get(0)?.title)

        assertEquals(2, list?.get(1)?.id)
        assertEquals("test2", list?.get(1)?.title)
    }

    @Test
    fun getNotesEmpty() = runBlocking {
        whenever(notesRepository.getNotes()).thenReturn( Result.success(listOf()))

        val result = getNotesUseCase.invoke(Unit)
        assertNotNull(result)

        val  list = result.getOrNull()
        assertEquals(list?.size, 0)
    }
}
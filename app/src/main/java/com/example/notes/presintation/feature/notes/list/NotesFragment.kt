package com.example.notes.presintation.feature.notes.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notes.R
import com.example.notes.presintation.feature.notes.list.adapter.NoteClickListener
import com.example.notes.presintation.feature.notes.list.adapter.NotesAdapter
import com.example.notes.presintation.feature.notes.state.NotesViewState
import com.example.notes.presintation.feature.notes.viewmodel.NotesViewModel
import com.example.notes.presintation.feature.notes.vo.NoteVO
import cz.eman.kaal.presentation.fragment.BaseFragment
import cz.eman.kaal.presentation.view.hide
import cz.eman.kaal.presentation.view.show
import kotlinx.android.synthetic.main.fragment_notes.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NotesFragment : BaseFragment() {

    private val viewModel by sharedViewModel<NotesViewModel>()
    private lateinit var notesAdapter: NotesAdapter

    private val noteClickListener: NoteClickListener = this::noteClicked


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // swipe to delete
        swipe.setOnRefreshListener {
            viewModel.reload()
        }

        addClickListeners()

        //init list
        initNoteList()

        // register fragment observers.
        registerObservers()

        // Load notes.
        if (!viewModel.hasData()) {
            viewModel.load()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeClickListeners()
    }

    /**
     * here we register observers listeners etc...
     */
    private fun registerObservers() {
        viewModel.viewState.observe(this, Observer { state ->
            updateViews(state)
        })
    }

    /**
     *  updates fragment views.
     */
    private fun updateViews(state: NotesViewState) {
        if (swipe.isRefreshing) {
            swipe.isRefreshing = false
        }

        when (state) {
            is NotesViewState.Loading -> {
                progressBar.show()
            }

            is NotesViewState.Loaded -> {
                progressBar.hide()
                notesAdapter.submitList(state.notes)
            }

            is NotesViewState.NoteAdded -> {
                progressBar.hide()
                Toast.makeText(requireContext(), R.string.note_added, Toast.LENGTH_SHORT).show()
            }

            is NotesViewState.NoteUpdated -> {
                progressBar.hide()
                Toast.makeText(requireContext(), R.string.note_updated, Toast.LENGTH_SHORT).show()
            }

            is NotesViewState.NoteDeleted -> {
                progressBar.hide()
                Toast.makeText(requireContext(), R.string.note_deleted, Toast.LENGTH_SHORT).show()
            }

            is NotesViewState.Error -> {
                progressBar.hide()

                state.error?.let {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }

                state.notes?.let {
                    notesAdapter.submitList(it)
                }
            }
        }
    }

    private fun initNoteList() {
        notesAdapter = NotesAdapter(noteClickListener)

        list.apply {
            adapter = notesAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun noteClicked(note: NoteVO) {
        viewModel.setSelectedNote(note)

        findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToNoteDetailFragment())
    }

    private fun addClickListeners() {
        addNote.setOnClickListener {
            addNoteClicked()
        }
    }

    private fun removeClickListeners() {
        addNote.setOnClickListener(null)
    }

    private fun addNoteClicked() {
        findNavController().navigate(NotesFragmentDirections.actionNotesFragmentToAddNoteFragment())
    }
}
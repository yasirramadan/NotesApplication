package com.example.notes.presintation.feature.notes.detail


import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.presintation.extention.enable
import com.example.notes.presintation.extention.hideKeyboard
import com.example.notes.presintation.feature.notes.viewmodel.NotesViewModel
import cz.eman.kaal.presentation.fragment.BaseFragment
import cz.eman.kaal.presentation.view.show
import kotlinx.android.synthetic.main.fragment_note_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class NoteDetailFragment : BaseFragment() {

    private val viewModel: NotesViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //init click listeners
        addClickListeners()

        registerObservers()

        //note detail text watcher
        noteDetail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                save.isEnabled = !TextUtils.isEmpty(s)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        removeListeners()
    }

    private fun registerObservers() {
        viewModel.selectedNote.observe(this, Observer { noteVO ->
            noteVO?.let {
                noteDetail.setText(noteVO.title)
            }
        })
    }

    private fun removeListeners() {
        editNote.setOnClickListener(null)
        deleteNote.setOnClickListener(null)
        save.setOnClickListener(null)
    }

    private fun addClickListeners() {

        //edit note fab click
        editNote.setOnClickListener {
            noteDetail.enable()
            save.show()

            floatingMenu.close(true)
        }

        //delete note fab click
        deleteNote.setOnClickListener {
            viewModel.deleteNote()

            findNavController().navigate(NoteDetailFragmentDirections.actionNoteDetailFragmentToNotesFragment())
        }

        //save click
        save.setOnClickListener {
            noteDetail.hideKeyboard()

            viewModel.updateNote(noteDetail.text?.toString())

            findNavController().navigate(NoteDetailFragmentDirections.actionNoteDetailFragmentToNotesFragment())
        }
    }
}
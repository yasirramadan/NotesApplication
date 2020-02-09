package com.example.notes.presintation.feature.notes.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.notes.R
import com.example.notes.presintation.extention.hideKeyboard
import com.example.notes.presintation.extention.showKeyboard
import com.example.notes.presintation.feature.notes.viewmodel.NotesViewModel
import cz.eman.kaal.presentation.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_add_note.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class AddNoteFragment : BaseFragment() {

    private val viewModel by sharedViewModel<NotesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_add_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //text watcher
        noteDescription.apply {
            addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable) {
                    add.isEnabled = !TextUtils.isEmpty(s)
                }

                override fun beforeTextChanged(
                    s: CharSequence,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            })

            showKeyboard()
        }

        //init add note click
        add.setOnClickListener {
            onAddNoteClicked()
        }
    }

    private fun onAddNoteClicked() {
        noteDescription.hideKeyboard()

        viewModel.addNote(noteDescription.text.toString())
        findNavController().navigate(AddNoteFragmentDirections.actionAddNoteFragmentToNotesFragment())
    }
}
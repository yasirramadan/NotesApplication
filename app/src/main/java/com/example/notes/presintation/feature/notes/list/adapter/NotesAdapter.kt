package com.example.notes.presintation.feature.notes.list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.presintation.feature.notes.vo.NoteVO
import kotlinx.android.synthetic.main.notes_item.view.*

/**
 * click listener for adapter items.
 */
typealias NoteClickListener = (NoteVO) -> Unit

class NotesAdapter(private val noteClickListener: NoteClickListener) :
    ListAdapter<NoteVO, NotesAdapter.NoteHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_item, parent, false)
        return NoteHolder(view)
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: NoteVO) {
            itemView.noteDescription.text = note.title

            itemView.setOnClickListener {
                noteClickListener(note)
            }
        }
    }


    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NoteVO>() {
            override fun areItemsTheSame(oldItem: NoteVO, newItem: NoteVO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: NoteVO, newItem: NoteVO): Boolean {
                return oldItem.title == newItem.title && oldItem.id == newItem.id
            }
        }
    }

}
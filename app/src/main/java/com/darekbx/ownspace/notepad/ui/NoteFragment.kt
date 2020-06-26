package com.darekbx.ownspace.notepad.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.darekbx.ownspace.R
import com.darekbx.ownspace.notepad.viewmodels.NotepadViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_note.*
import javax.inject.Inject

@AndroidEntryPoint
class NoteFragment : Fragment() {

    companion object {

        private const val ARG_NOTE_INDEX = "note_index"

        @JvmStatic
        fun newInstance(noteIndex: Int): NoteFragment {
            return NoteFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_NOTE_INDEX, noteIndex)
                }
            }
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var notepadViewModel: NotepadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notepadViewModel =
            ViewModelProvider(this, viewModelFactory)[NotepadViewModel::class.java].apply {
                arguments?.getInt(ARG_NOTE_INDEX)?.let { noteIndex ->
                    fetch(noteIndex).observe(this@NoteFragment, Observer { contents ->
                        note_contents.setText(contents)
                    })
                }
            }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.fragment_note, container, false)

    override fun onPause() {
        super.onPause()
        arguments?.getInt(ARG_NOTE_INDEX)?.let { noteIndex ->
            notepadViewModel.update(noteIndex, note_contents.text.toString())
        }
    }
}
package com.darekbx.ownspace.notepad.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.darekbx.ownspace.R
import com.darekbx.ownspace.notepad.viewmodels.NotepadViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_notepad.*
import javax.inject.Inject

@AndroidEntryPoint
class NotepadActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var notepadViewModel: NotepadViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notepad)

        notepadViewModel = ViewModelProvider(this, viewModelFactory)[NotepadViewModel::class.java].apply {
            fetchIndexes().observe(this@NotepadActivity, Observer { indexes ->
                setUpTabs(indexes)
            })
        }
    }

    private fun setUpTabs(indexes: List<Int>) {
        val sectionsPagerAdapter = NotesPagerAdapter(supportFragmentManager, indexes)
        notepad_view_pager.adapter = sectionsPagerAdapter
        notepad_tabs.setupWithViewPager(notepad_view_pager)
    }
}
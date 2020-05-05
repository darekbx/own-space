package com.darekbx.ownspace.notepad.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class NotesPagerAdapter(fragmentManager: FragmentManager, val indexes: List<Int>) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return NoteFragment.newInstance(indexes.get(position))
    }

    override fun getPageTitle(position: Int) = "${(65 + position).toChar()}"

    override fun getCount() = indexes.size
}
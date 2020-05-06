package com.darekbx.ownspace.common.di

import com.darekbx.ownspace.ApplicationsActivity
import com.darekbx.ownspace.common.di.viewmodels.ViewModelModule
import com.darekbx.ownspace.notepad.ui.NoteFragment
import com.darekbx.ownspace.notepad.ui.NotepadActivity
import com.darekbx.ownspace.tasks.ui.TaskActivity
import com.darekbx.ownspace.tasks.ui.TasksActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(applicationsActivity: ApplicationsActivity)

    // Notepad
    fun inject(notepadActivity: NotepadActivity)
    fun inject(noteFragment: NoteFragment)

    // Tasks
    fun inject(tasksActivity: TasksActivity)
    fun inject(taskActivity: TaskActivity)
}
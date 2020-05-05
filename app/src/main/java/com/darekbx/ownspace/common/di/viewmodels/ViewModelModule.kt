package com.darekbx.ownspace.common.di.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.darekbx.ownspace.notepad.viewmodels.NotepadViewModel
import com.darekbx.ownspace.tasks.viewmodels.TaskViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelFactory.ViewModelKey(NotepadViewModel::class)
    internal abstract fun bindNotepadViewModel(viewModel: NotepadViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelFactory.ViewModelKey(TaskViewModel::class)
    internal abstract fun bindTaskViewModel(viewModel: TaskViewModel): ViewModel
}
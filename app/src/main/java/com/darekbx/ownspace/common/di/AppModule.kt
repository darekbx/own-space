package com.darekbx.ownspace.common.di

import android.content.Context
import androidx.room.Room
import com.darekbx.ownspace.common.storage.AppDatabase
import com.darekbx.ownspace.common.utils.PermissionsHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
object AppModule {

    @Provides
    fun providePermissionsHelper() = PermissionsHelper()

    @Provides
    fun provideNotepadDao(database: AppDatabase) = database.notepadDao()

    @Provides
    fun provideTaskDao(database: AppDatabase) = database.taskDao()

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME).build()
}
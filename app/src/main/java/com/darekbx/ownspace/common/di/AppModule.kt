package com.darekbx.ownspace.common.di

import android.content.Context
import androidx.room.Room
import com.darekbx.ownspace.OwnSpaceApplication
import com.darekbx.ownspace.common.storage.AppDatabase
import com.darekbx.ownspace.common.utils.PermissionsHelper
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: OwnSpaceApplication) {

    @Provides
    fun provideApplication() = application

    @Provides
    fun provideContext(): Context = application

    @Provides
    fun providePermissionsHelper() = PermissionsHelper()

    @Provides
    fun provideNotepadDao(database: AppDatabase) = database.notepadDao()

    @Provides
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME).build()
}
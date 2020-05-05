package com.darekbx.ownspace.common.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.darekbx.ownspace.common.storage.dao.NotepadDao
import com.darekbx.ownspace.common.storage.entities.NotepadDto
import com.darekbx.ownspace.common.storage.entities.TaskDto

@Database(entities = arrayOf(NotepadDto::class, TaskDto::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        val DB_NAME = "own_space_db"
    }

    abstract fun notepadDao(): NotepadDao
}
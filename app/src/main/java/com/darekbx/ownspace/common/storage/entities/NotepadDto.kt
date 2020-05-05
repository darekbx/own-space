package com.darekbx.ownspace.common.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notepad")
class NotepadDto(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "index") var index: Int = 0,
    @ColumnInfo(name = "contents") var contents: String = ""
)

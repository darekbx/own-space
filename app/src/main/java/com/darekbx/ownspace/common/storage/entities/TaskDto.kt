package com.darekbx.ownspace.common.storage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
class TaskDto(
    @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "content") var content: String = "",
    @ColumnInfo(name = "date") var date: String = "",
    @ColumnInfo(name = "flag") var flag: Int = 0
)
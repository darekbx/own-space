package com.darekbx.ownspace.common.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.darekbx.ownspace.common.storage.entities.TaskDto

@Dao
interface TaskDao {

    @Query("SELECT COUNT(*) FROM task")
    fun count(): Int

    @Insert
    fun add(taskDto: TaskDto): Long

    @Query("SELECT * FROM task")
    fun fetch(): LiveData<List<TaskDto>>
}
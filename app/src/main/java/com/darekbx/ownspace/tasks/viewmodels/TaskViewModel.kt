package com.darekbx.ownspace.tasks.viewmodels

import android.os.Environment
import androidx.lifecycle.Transformations
import com.darekbx.ownspace.common.BaseViewModel
import com.darekbx.ownspace.common.storage.dao.TaskDao
import com.darekbx.ownspace.common.storage.entities.TaskDto
import com.darekbx.ownspace.tasks.model.Task
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

class TaskViewModel @Inject constructor(
    private val taskDao: TaskDao
) : BaseViewModel() {

    fun import() {
        ioScope.launch {
            if (taskDao.count() == 0) {
                val dir = "${Environment.getExternalStorageDirectory()}/tasks"
                val legacyFile = "tasks.bpk"
                val backupFile = File(dir, legacyFile)

                val taskDelimiter = "###"
                val itemDelimiter = "%%"

                backupFile
                    .readText()
                    .split(taskDelimiter)
                    .filter { it.length > 0 }
                    .map { taskContent ->
                        val chunks = taskContent.split(itemDelimiter)
                        val name = chunks[0]
                        val flag = Task.Flag.fromInt(chunks[1].toInt())
                        val date = chunks[2]
                        val content = chunks[3]
                        val task = Task(null, name, content, date, flag)
                        taskDao.add(task.toTaskDto())
                    }
            }
        }
    }

    fun fetch(id: Long) = Transformations.map(
        taskDao.fetch(id),
        { taskDto -> taskDto.toTask() }
    )

    fun fetch() = Transformations.map(
        taskDao.fetch(),
        { dtos ->
            dtos.map { taskDto ->
                taskDto.toTask()
            }
        })

    private fun Task.toTaskDto() = TaskDto(null, name, contents, date, flag.value)

    private fun TaskDto.toTask() = Task(id, name, content, date, Task.Flag.fromInt(flag))
}
package com.darekbx.ownspace.tasks.viewmodels

import android.os.Environment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.darekbx.ownspace.common.BaseViewModel
import com.darekbx.ownspace.common.storage.dao.TaskDao
import com.darekbx.ownspace.common.storage.entities.TaskDto
import com.darekbx.ownspace.tasks.model.Task
import kotlinx.coroutines.launch
import java.io.File
import java.util.*
import javax.inject.Inject

class TaskViewModel @Inject constructor(
    private val taskDao: TaskDao
) : BaseViewModel() {

    enum class Status {
        IMPORT_SUCCESS
    }

    var status = MutableLiveData<Status>()

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
                    .map { taskContent ->
                        val chunks = taskContent.split(itemDelimiter)
                        val name = chunks[0]
                        val flag = Task.Flag.fromInt(chunks[1].toInt())
                        val date = Date(chunks[2])
                        val content = chunks[3]
                        val task = Task(name, content, date, flag)
                        taskDao.add(task.toTaskDto())
                    }

                status.postValue(Status.IMPORT_SUCCESS)
            }
        }
    }

    fun fetch() = Transformations.map(
        taskDao.fetch(),
        { dtos ->
            dtos.map { taskDto ->
                taskDto.toTask()
            }
        })

    private fun Task.toTaskDto() = TaskDto(null, name, contents, date.time, flag.value)

    private fun TaskDto.toTask() = Task(name, content, Date(date), Task.Flag.fromInt(flag))
}
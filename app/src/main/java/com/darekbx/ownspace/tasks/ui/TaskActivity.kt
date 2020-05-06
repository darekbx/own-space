package com.darekbx.ownspace.tasks.ui

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.darekbx.ownspace.OwnSpaceApplication
import com.darekbx.ownspace.R
import com.darekbx.ownspace.tasks.model.Task
import com.darekbx.ownspace.tasks.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.activity_task.*
import javax.inject.Inject

class TaskActivity : AppCompatActivity() {

    companion object {
        val ARG_TASK_ID = "task_id_key"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task)
        (application as OwnSpaceApplication).appComponent.inject(this)

        taskViewModel = ViewModelProvider(this, viewModelFactory)[TaskViewModel::class.java]
        loadTask()
    }

    private fun loadTask() {
        intent.getLongExtra(ARG_TASK_ID, -1L)
            ?.takeIf { it >= 0L }
            ?.let { id ->
                taskViewModel.fetch(id).observe(this@TaskActivity, Observer {
                    displayTask(it)
                })
            }
    }

    private fun displayTask(task: Task) {
        // Add scroll to text view
        task_content.setMovementMethod(ScrollingMovementMethod())

        task_name.setText(task.name)
        task_content.setText(task.contents)
    }
}
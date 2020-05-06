package com.darekbx.ownspace.tasks.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.darekbx.ownspace.OwnSpaceApplication
import com.darekbx.ownspace.R
import com.darekbx.ownspace.tasks.model.Task
import com.darekbx.ownspace.tasks.viewmodels.TaskViewModel
import kotlinx.android.synthetic.main.activity_tasks.*
import javax.inject.Inject

class TasksActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        (application as OwnSpaceApplication).appComponent.inject(this)

        val adapter = TasksAdapter(this)
        tasks_list_view.adapter = adapter
        tasks_list_view.setOnItemClickListener { parent, view, position, id ->
            adapter.getItem(position)?.let {
                openTask(it)
            }
        }

        taskViewModel = ViewModelProvider(this, viewModelFactory)[TaskViewModel::class.java].apply {
            import()
            fetch().observe(this@TasksActivity, Observer { tasks ->
                displayTasks(adapter, tasks)
            })
        }
    }

    private fun openTask(item: Task) {
        val intent = Intent(this@TasksActivity, TaskActivity::class.java).apply {
            putExtra(TaskActivity.ARG_TASK_ID, item.id)
        }
        startActivity(intent)
    }

    private fun displayTasks(adapter: TasksAdapter, tasks: List<Task>) {
        with(adapter) {
            clear()
            addAll(tasks)
            notifyDataSetChanged()
        }
    }
}
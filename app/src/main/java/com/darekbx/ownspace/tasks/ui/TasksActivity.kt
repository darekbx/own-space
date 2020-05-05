package com.darekbx.ownspace.tasks.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.darekbx.ownspace.OwnSpaceApplication
import com.darekbx.ownspace.R
import com.darekbx.ownspace.tasks.viewmodels.TaskViewModel
import javax.inject.Inject

class TasksActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        (application as OwnSpaceApplication).appComponent.inject(this)

        taskViewModel = ViewModelProvider(this, viewModelFactory)[TaskViewModel::class.java].apply {
            fetch().observe(this@TasksActivity, Observer { tasks ->

            })
        }
    }
}
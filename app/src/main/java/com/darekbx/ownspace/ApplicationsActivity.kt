package com.darekbx.ownspace

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.darekbx.ownspace.common.utils.PermissionsHelper
import com.darekbx.ownspace.notepad.ui.NotepadActivity
import com.darekbx.ownspace.tasks.ui.TasksActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ApplicationsActivity : AppCompatActivity() {

    @Inject
    lateinit var permissionsHelper: PermissionsHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_applications)
    }

    override fun onResume() {
        super.onResume()
        handlePermissions()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PermissionsHelper.PERMISSIONS_REQUEST_CODE) {
            val anyDenied = grantResults.any { it == PackageManager.PERMISSION_DENIED }
            when (anyDenied) {
                true -> Toast.makeText(applicationContext, R.string.permissions_are_required, Toast.LENGTH_SHORT).show()
                else -> loadInfoCard()
            }
        }
    }

    fun loadInfoCard() {

    }

    fun onTasksClick(view: View) {
        startActivity(Intent(this, TasksActivity::class.java))
    }

    fun onNotepadClick(view: View) {
        startActivity(Intent(this, NotepadActivity::class.java))
    }

    private fun handlePermissions() {
        val hasPermissions = permissionsHelper.checkAllPermissionsGranted(this)
        when (hasPermissions) {
            true -> loadInfoCard()
            else -> permissionsHelper.requestPermissions(this)
        }
    }
}

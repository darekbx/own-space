package com.darekbx.ownspace.common.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class PermissionsHelper {

    companion object {
        val PERMISSIONS_REQUEST_CODE = 1000
        val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    fun checkAllPermissionsGranted(context: Context) = REQUIRED_PERMISSIONS
        .all { ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED }

    fun requestPermissions(activity: Activity) {
        activity.requestPermissions(
            REQUIRED_PERMISSIONS,
            PERMISSIONS_REQUEST_CODE
        )
    }
}
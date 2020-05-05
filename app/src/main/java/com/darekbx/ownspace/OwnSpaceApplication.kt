package com.darekbx.ownspace

import android.app.Application
import com.darekbx.ownspace.common.di.AppModule
import com.darekbx.ownspace.common.di.DaggerAppComponent

class OwnSpaceApplication: Application() {

    val appComponent = DaggerAppComponent
        .builder()
        .appModule(AppModule(this))
        .build()
}
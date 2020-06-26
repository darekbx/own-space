package com.darekbx.ownspace.weight.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.darekbx.ownspace.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)
    }
}
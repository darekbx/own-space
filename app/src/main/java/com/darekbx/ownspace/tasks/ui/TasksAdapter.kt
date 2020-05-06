package com.darekbx.ownspace.tasks.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.darekbx.ownspace.R
import com.darekbx.ownspace.databinding.AdapterTaskBinding
import com.darekbx.ownspace.tasks.model.Task

class TasksAdapter(context: Context) : ArrayAdapter<Task>(context, R.layout.adapter_task) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding = (when (convertView) {
            null -> DataBindingUtil.inflate(inflater, R.layout.adapter_task, parent, false)
            else ->DataBindingUtil.getBinding<AdapterTaskBinding>(convertView)
        } as AdapterTaskBinding).apply {
            task = getItem(position)
        }
        return binding.root
    }

    private val inflater: LayoutInflater by lazy { LayoutInflater.from(context) }
}
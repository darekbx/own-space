package com.darekbx.ownspace.tasks.model

import com.darekbx.ownspace.R

class Task(val id: Long?, val name: String, val contents: String, val date: String, val flag: Flag) {

    enum class Flag(val value: Int) {
        LOW(0),
        NORMAL(1),
        HIGH(2);

        companion object {
            fun fromInt(value: Int) =
                values().associateBy(Flag::value)[value] ?: LOW
        }
    }

    fun getPriorityImage() =
        when (flag) {
            Flag.LOW -> R.drawable.ic_task_priority_low
            Flag.HIGH -> R.drawable.ic_task_priority_high
            else -> R.drawable.ic_tasks
        }
}
package com.darekbx.ownspace.tasks.model

import java.util.Date

class Task(val name: String, val contents: String, val date: Date, val flag: Flag) {

    enum class Flag(val value: Int) {
        LOW(0),
        NORMAL(1),
        HIGH(2);

        companion object {
            fun fromInt(value: Int) =
                values().associateBy(Flag::value)[value] ?: LOW
        }
    }
}
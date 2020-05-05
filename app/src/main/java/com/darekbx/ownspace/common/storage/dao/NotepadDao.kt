package com.darekbx.ownspace.common.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.darekbx.ownspace.common.storage.entities.NotepadDto

@Dao
interface NotepadDao {

    @Insert
    fun add(notepad: NotepadDto): Long

    @Query("SELECT COUNT(*) FROM `notepad`")
    fun countIndexes(): Int

    @Query("SELECT `index` FROM `notepad`")
    fun fetchIndexes(): LiveData<List<Int>>

    @Query("SELECT `contents` FROM `notepad` WHERE `index` = :index LIMIT 1")
    fun fetch(index: Int): LiveData<String>

    @Query("SELECT MAX(`index`) FROM `notepad`")
    fun fetchMaxIndex() : Int

    @Query("UPDATE `notepad` SET `contents` = :contents WHERE `index` = :index")
    fun update(index: Int, contents: String)

    @Query("DELETE FROM `notepad` WHERE `index` = :index")
    fun delete(index: Int)
}
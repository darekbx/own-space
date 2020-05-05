package com.darekbx.ownspace.notepad.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darekbx.ownspace.common.BaseViewModel
import com.darekbx.ownspace.common.storage.dao.NotepadDao
import com.darekbx.ownspace.common.storage.entities.NotepadDto
import kotlinx.coroutines.launch
import javax.inject.Inject

class NotepadViewModel @Inject constructor(
    private val notepadDao: NotepadDao
) : BaseViewModel() {

    enum class Status {
        ADD_SUCCESS,
        UPDATE_SUCCESS,
        DELETE_SUCCESS
    }

    var status = MutableLiveData<Status>()

    fun fetch(index: Int) = notepadDao.fetch(index)

    fun fetchIndexes(): LiveData<List<Int>> {
        ioScope.launch {
            val count = notepadDao.countIndexes()
            if (count == 0) {
                // By default add two empty notes
                arrayOf(0, 1).forEach { i ->
                    notepadDao.add(NotepadDto(i.toLong(), i, "Empty"))
                }
            }
        }
        return notepadDao.fetchIndexes()
    }

    fun add() {
        ioScope.launch {
            val highestIndex = notepadDao.fetchMaxIndex()
            val dto = NotepadDto(null, highestIndex + 1, "")
            notepadDao.add(dto)
            status.postValue(Status.ADD_SUCCESS)
        }
    }

    fun update(index: Int, contents: String) {
        ioScope.launch {
            notepadDao.update(index, contents)
            status.postValue(Status.UPDATE_SUCCESS)
        }
    }

    fun delete(index: Int) {
        ioScope.launch {
            notepadDao.delete(index)
            status.postValue(Status.DELETE_SUCCESS)
        }
    }
}
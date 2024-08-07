package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.database.NoteDao
import com.example.notes.model.Note

class NotesRepository(private val noteDao: NoteDao) {

    suspend fun insert(note: Note){
        noteDao.insert(note)
    }
    suspend fun delete(note: Note){
        noteDao.delete(note)
    }
    suspend fun update(note:Note){
        noteDao.update(note)
    }
    fun getAllNotes():LiveData<List<Note>>{
        return noteDao.getAllNote()
    }

}
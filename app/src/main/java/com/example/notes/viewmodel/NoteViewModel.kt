package com.example.notes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.notes.database.NotesDatabase
import com.example.notes.model.Note
import com.example.notes.repository.NotesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application,private val repository: NotesRepository): AndroidViewModel(application) {

  fun addNote(note:Note)= viewModelScope.launch(Dispatchers.IO) {
      repository.insert(note)
  }
    fun deleteNote(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun updateNote(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.update(note)
    }
    fun getAllNotes():LiveData<List<Note>>{
        return repository.getAllNotes()
    }

}
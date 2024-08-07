package com.example.notes.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notes.repository.NotesRepository

class  NoteViewModeFactory(val app:Application,private val repository: NotesRepository):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(app, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
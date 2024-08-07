package com.example.notes

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.notes.database.NotesDatabase
import com.example.notes.repository.NotesRepository
import com.example.notes.viewmodel.NoteViewModeFactory
import com.example.notes.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        setUpViewModel()

    }
    private fun setUpViewModel(){
        val notesRepository = NotesRepository(NotesDatabase.getDatabase(application).notesDao())
        val viewModelFactory = NoteViewModeFactory(application,notesRepository)
        viewModel=ViewModelProvider(this,viewModelFactory).get(NoteViewModel::class.java )
    }
}
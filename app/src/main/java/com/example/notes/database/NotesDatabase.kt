package com.example.notes.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notes.model.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase:RoomDatabase() {

    abstract fun notesDao(): NoteDao

    companion object{
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    "notes_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

    }

}
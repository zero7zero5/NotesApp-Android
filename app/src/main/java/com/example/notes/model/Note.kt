package com.example.notes.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes_table")
data class Note(
    @ColumnInfo(name = "title") var title:String,
    @ColumnInfo(name = "content") var content:String,
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id:Int=0
):Parcelable

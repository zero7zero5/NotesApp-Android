package com.example.notes.adapter

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.R
import com.example.notes.fragments.HomeFragment
import com.example.notes.fragments.HomeFragmentDirections
import com.example.notes.model.Note
import java.util.Random

class NotesAdapter: RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var notes= ArrayList<Note>()

    inner class NotesViewHolder(view:View):RecyclerView.ViewHolder(view){
        var title = view.findViewById<TextView>(R.id.title)
        var description = view.findViewById<TextView>(R.id.description)
        var notesCard = view.findViewById<CardView>(R.id.notesCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.notes_view,parent,false)
        return NotesViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        var currentNote = notes[position]
        holder.title.text=currentNote.title
        holder.description.text=currentNote.content
        var color = generateRandomColor()
        holder.notesCard.setCardBackgroundColor(color)

        holder.notesCard.setOnClickListener{view->
           val direction = HomeFragmentDirections.homeToEdit(currentNote,color)
            view.findNavController().navigate(direction)
        }


    }
    fun updateList(newList:List<Note>){
        notes.clear()
        notes.addAll(newList)
        notifyDataSetChanged()
    }

    fun generateRandomColor():Int{
        val random = Random()
        var color = Color.argb(255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256))
        return color
    }
}
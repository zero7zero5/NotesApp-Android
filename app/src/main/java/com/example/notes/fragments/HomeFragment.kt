package com.example.notes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.adapter.NotesAdapter
import com.example.notes.databinding.FragmentHomeBinding
import com.example.notes.viewmodel.NoteViewModel


class HomeFragment : Fragment(R.layout.fragment_home) {
    private var _binding:FragmentHomeBinding?=null

    private val binding get() = _binding!!

    private lateinit var viewModel:NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel=(activity as MainActivity).viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.createButton.setOnClickListener{
            findNavController().navigate(R.id.homeToAdd)

        }

        val adapter = NotesAdapter()
        val list = binding.notesList
        list.adapter=adapter
        list.layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        list.setHasFixedSize(true)

        viewModel.getAllNotes().observe(viewLifecycleOwner, Observer {list->
            list?.let {
                adapter.updateList(it)
            }
        })
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}
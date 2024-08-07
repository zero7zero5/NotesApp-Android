package com.example.notes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.databinding.FragmentAddBinding
import com.example.notes.model.Note
import com.example.notes.viewmodel.NoteViewModel


class AddFragment : Fragment(R.layout.fragment_add) {
    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: NoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddBinding.inflate(inflater, container, false)
        viewModel = (activity as MainActivity).viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveButton.setOnClickListener {
            saveNote()
        }
        binding.back.setOnClickListener{
            findNavController().navigateUp()
        }

    }
    private fun saveNote() {
        val title = binding.title.text.toString().trim()
        val description = binding.description.text.toString().trim()

        if (title.isNotEmpty()) {
            val note = Note(title, description)
            viewModel.addNote(note)

            findNavController().navigate(R.id.addToHome, null, navOptions {
                popUpTo(findNavController().graph.startDestinationId) {
                    inclusive = true
                }
            })
        }
    }
}
package com.example.notes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.navOptions
import com.example.notes.MainActivity
import com.example.notes.R
import com.example.notes.databinding.FragmentEditBinding
import com.example.notes.model.Note
import com.example.notes.viewmodel.NoteViewModel

class EditFragment : Fragment(R.layout.fragment_edit) {
    private var _binding:FragmentEditBinding?=null
    private val binding get() = _binding!!
    private lateinit var viewModel: NoteViewModel

    private val args:EditFragmentArgs by navArgs()
    private lateinit var note:Note

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditBinding.inflate(inflater, container, false)
        viewModel=(activity as MainActivity).viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        note=args.note!!
        val color=args.color!!

        binding.editFragment.setBackgroundColor(color)
        binding.editTitle.setText(note.title)
        binding.editDescription.setText(note.content)

        val navController = findNavController()


        binding.deleteButton.setOnClickListener {
            deleteNote(note)
            navController.navigate(R.id.editToHome, null, navOptions {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            })
        }

        binding.editButton.setOnClickListener {
            updateNote(note)
            navController.navigate(R.id.editToHome, null, navOptions {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            })
        }
        binding.backButton.setOnClickListener{
            findNavController().navigateUp()
        }
    }

    private fun deleteNote(note:Note){
        viewModel.deleteNote(note)
    }
    private fun updateNote(note:Note){
        if(binding.editTitle.text.isNotEmpty()){
            note.title=binding.editTitle.text.toString()
            note.content=binding.editDescription.text.toString()

            viewModel.updateNote(note)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}
package com.creativeitinstitute.maadbootcampproject1.addTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner

import androidx.lifecycle.MutableLiveData
import com.creativeitinstitute.maadbootcampproject1.R
import com.creativeitinstitute.maadbootcampproject1.databinding.FragmentAddTaskBinding
import com.creativeitinstitute.maadbootcampproject1.utils.showChar
import com.creativeitinstitute.maadbootcampproject1.utils.showSnackBar
import com.google.android.material.snackbar.Snackbar

class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding

    private val viewModel by viewModels<AddTaskViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_add_task, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner

        addTask()

        return binding.root
    }

    private fun addTask() {
        binding.saveTaskBtn.setOnClickListener {
            val title = binding.addTaskTitleEdt.text.toString().trim()
            val description = binding.addTaskDescriptionEdt.text.toString().trim()

            viewModel.saveTask()

        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindSnackBar(view)
        bindUiMessage()
    }

    private fun bindUiMessage() {

        binding.warningTextTaskTitle.showChar(viewLifecycleOwner, viewModel.title)
        binding.warningTextTaskDescription.showChar(viewLifecycleOwner, viewModel.description)

    }

    private fun bindSnackBar(view: View) {

        view.showSnackBar(
            lifecycleOwner = viewLifecycleOwner,
            viewModel.snackBarMsg,
            Snackbar.LENGTH_LONG
        )

    }
}


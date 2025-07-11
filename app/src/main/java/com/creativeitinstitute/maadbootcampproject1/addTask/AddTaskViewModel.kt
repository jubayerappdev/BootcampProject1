package com.creativeitinstitute.maadbootcampproject1.addTask


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.creativeitinstitute.maadbootcampproject1.R
import com.creativeitinstitute.maadbootcampproject1.data.Task
import com.creativeitinstitute.maadbootcampproject1.data.source.DefaultTaskRepository
import com.creativeitinstitute.maadbootcampproject1.utils.toTrimedString
import kotlinx.coroutines.launch

@Suppress(
    "NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS",
    "RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS"
)
class AddTaskViewModel(private val application: Application) : AndroidViewModel(application) {

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val repository: DefaultTaskRepository = DefaultTaskRepository.getInstance(application)

    private val _snackBarMsg = MutableLiveData<Int>()
    val snackBarMsg: LiveData<Int>
        get() = _snackBarMsg

    val titleLength = 6



    fun saveTask() {

        val currentTitle = title.value
        val currentDescription = description.value


        if (isValidation(currentTitle, currentDescription)) {
            val task =
                Task(
                    title = currentTitle.toTrimedString(),
                    description = currentDescription.toTrimedString()
                )
            createTask(task)
        }


    }

    private fun isValidation(currentTitle: String, currentDescription: String): Boolean {

        if (currentTitle.isNullOrEmpty() || currentDescription.isNullOrEmpty()) {

            _snackBarMsg.postValue(R.string.empty_task_message)

            return false
        }

        if (currentTitle.toString().length < titleLength) {
            _snackBarMsg.postValue(R.string.title_must_be_6_char_or_more)

            return false
        }
        return true
    }

    private fun createTask(task: Task) {

        viewModelScope.launch {
            repository.saveTask(task)
        }


    }

}
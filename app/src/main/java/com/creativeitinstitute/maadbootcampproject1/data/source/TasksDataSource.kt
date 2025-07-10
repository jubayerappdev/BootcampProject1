package com.creativeitinstitute.maadbootcampproject1.data.source

import com.creativeitinstitute.maadbootcampproject1.data.Task

interface TasksDataSource {

    suspend fun saveTask(task: Task)


}
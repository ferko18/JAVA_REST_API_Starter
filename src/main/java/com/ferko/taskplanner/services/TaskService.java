package com.ferko.taskplanner.services;

import com.ferko.taskplanner.models.Task;

import java.util.List;

public interface TaskService
{
//read task info
    //get al tasks
    List<Task> findAllTasks();
    //get task by id
    Task findTaskById(Integer id);
    //get expired tasks

    List <Task> listExpiredTasks();


}

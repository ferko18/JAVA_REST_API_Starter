package com.ferko.taskplanner.services;

import com.ferko.taskplanner.models.Task;
import com.ferko.taskplanner.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service (value = "taskService")
public class TaskServiceImpl implements TaskService
{
private TaskRepository taskrepo;
@Autowired
public TaskServiceImpl(TaskRepository tr)
{
    this.taskrepo =tr;
}


    @Override
    public List<Task> findAllTasks()
    {
        List<Task> tasklist = new ArrayList<>();
        taskrepo.findAll().iterator().forEachRemaining(tasklist::add);
        return tasklist;
    }

    @Override
    public Task findTaskById(Integer id)
    {
        //FERKO: change this during exception handling
        return taskrepo.findById(id).orElse(null);
    }

    @Override
    public List<Task> listExpiredTasks()
    {
        List <Task> expiredtasks = new ArrayList<>();
        taskrepo.overdueTasks().iterator().forEachRemaining(expiredtasks::add);
        return expiredtasks;
    }


}

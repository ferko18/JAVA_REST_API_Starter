package com.ferko.taskplanner.services;

import com.ferko.taskplanner.models.Agenda;
import com.ferko.taskplanner.models.Checklist;
import com.ferko.taskplanner.models.Task;
import com.ferko.taskplanner.repository.ChecklistRepository;
import com.ferko.taskplanner.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "taskService")
public class TaskServiceImpl implements TaskService
{
    //constructor injection (I can use field injection but not recommended)
    private TaskRepository taskrepo;
    @Autowired
    public TaskServiceImpl(TaskRepository tr)
    {
        this.taskrepo = tr;
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
        List<Task> expiredtasks = new ArrayList<>();
        taskrepo.overdueTasks().iterator().forEachRemaining(expiredtasks::add);
        return expiredtasks;
    }

    @Override
    @Transactional
    public Task addTask(Task task)
    {
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setDuedate(task.getDuedate());

        //save transient before flushing (save task table before adding checklist and agenda)
        taskrepo.save(newTask);

        //loop through the checklist array and add them to newTask using the helper method
        for (Checklist item : task.getChecklistitems())
        {
            newTask.addChecklistItem(item);
        }
        //loop through the agenda array from task (which will come from request body) and add them to newTask using the helper method
        for (Agenda agenda : task.getAgendas())
        {
            newTask.addAgenda(agenda);
        }
        return taskrepo.save(newTask);

    }

    @Override
    @Transactional
    public Task editTask(Task t, Integer id)
    {
        //FERKO: to be handelled properly
        Task temp = taskrepo.findById(id).orElseThrow(null);
        if (t.getTitle() != null)
        {
            temp.setTitle(t.getTitle());
        }
        if (t.getDescription() != null)
        {
            temp.setDescription(t.getDescription());
        }
        if (t.getDuedate() != null)
        {
            temp.setDuedate(t.getDuedate());
        }

        if (t.getChecklistitems() != null)
        {
            temp.setChecklistitems(t.getChecklistitems());
        }

        if (t.getAgendas() != null)
        {
            temp.setAgendas(t.getAgendas());
        }

        return taskrepo.save(temp);

    }

    @Override
    @Transactional
    public void deleteTask(Integer id)
    {
        if (taskrepo.findById(id).isPresent())
        {
            taskrepo.deleteById(id);
        } else
        {
            //FERKO to be handelled
        }
    }

}

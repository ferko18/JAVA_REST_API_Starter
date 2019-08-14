package com.ferko.taskplanner.controllers;

import com.ferko.taskplanner.models.Task;
import com.ferko.taskplanner.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController
{
    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/tasks",
            produces = {"application/json"})
    public ResponseEntity<?> listAllTasks()
    {
        List<Task> tasks = taskService.findAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping(value = "/expired",
            produces = {"application/json"})
    public ResponseEntity<?> listExpiredTasks()
    {
        List<Task> expiredtasks = taskService.listExpiredTasks();
        return new ResponseEntity<>(expiredtasks, HttpStatus.OK);
    }

    @GetMapping(value = "/task/{id}", produces = {"application/json"})
    public ResponseEntity<?> findTaskById(@PathVariable Integer id)
    {
        Task task = taskService.findTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping(value = "/add",
            consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> addTask(@RequestBody Task t)
    {
        Task task = taskService.addTask(t);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PutMapping(value = "/edit/{id}", consumes = {"application/json"},
            produces = {"application/json"})
    public ResponseEntity<?> updateTask(@RequestBody Task t, @PathVariable Integer id)
    {
        Task task = taskService.editTask(t, id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id)
    {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }
}

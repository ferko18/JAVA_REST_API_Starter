package com.ferko.taskplanner.repository;

import com.ferko.taskplanner.models.Task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TaskRepository extends CrudRepository <Task, Integer>
{
    @Query(value = "select * from tasks where duedate < (current_timestamp-interval '1 day')", nativeQuery = true)
    List<Task> overdueTasks ();
}

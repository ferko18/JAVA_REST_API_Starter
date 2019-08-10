package com.ferko.taskplanner.repository;

import com.ferko.taskplanner.models.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository <Task, Integer>
{
}

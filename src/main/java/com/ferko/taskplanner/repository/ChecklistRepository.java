package com.ferko.taskplanner.repository;

import com.ferko.taskplanner.models.Checklist;
import org.springframework.data.repository.CrudRepository;

public interface ChecklistRepository extends CrudRepository<Checklist, Integer>
{
}

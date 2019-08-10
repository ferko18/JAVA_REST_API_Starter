package com.ferko.taskplanner.repository;

import com.ferko.taskplanner.models.Agenda;
import org.springframework.data.repository.CrudRepository;

public interface AgendaRepository extends CrudRepository<Agenda, Integer>
{

}

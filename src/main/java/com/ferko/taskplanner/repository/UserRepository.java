package com.ferko.taskplanner.repository;

import com.ferko.taskplanner.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer>
{
}

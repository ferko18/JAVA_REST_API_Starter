package com.ferko.taskplanner.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "roles", schema = "public", catalog = "taskplanner")
@Data public class Role
{
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users = new ArrayList<>();

    //user helper methods
    public void addUserToRole(User user)
    {
        users.add(user);
        user.getRoles().add(this);
    }

    public void removeUserfromRole(User user)
    {
        users.remove(user);
        user.getRoles().remove(this);
    }
}

package com.ferko.taskplanner.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public", catalog = "taskplanner")
@Data public class User
{
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "first_name", nullable = false, length = 255)
    private String firstName;

    @Basic
    @Column(name = "last_name", nullable = false, length = 255)
    private String lastName;

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    private String email;

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    //relationships*************************************************************/
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<Role> roles = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "task_owners", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> usersOwningTask = new ArrayList<>();

    @ManyToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="attendants", joinColumns =@JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="task_id"))
    private List<Task> tasksAssignedToUser = new ArrayList<>();


    //helper methods*********************************************************/
    //helper method to add role to a user
    public void addRole(Role role)
    {
        roles.add(role);
        role.getUsers().add(this);
    }

    //helper method to remove user from role
    public void removeRole(Role role )
    {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    //helper method to add task to attendant
    public void addTasksToAttendant(Task task)
    {
        tasksAssignedToUser.add(task);
        task.getAttendants().add(this);
    }

    public void removeTasksFromAttendant(Task task)
    {
        tasksAssignedToUser.remove(task);
        task.getAttendants().remove(this);
    }





}

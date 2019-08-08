package com.ferko.taskplanner.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public", catalog = "taskplanner")
public class User
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

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<Role> roles = new ArrayList<>();
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

    @OneToMany(mappedBy = "taskowner", cascade = CascadeType.ALL)
    private List<Task> tasksOwnedByUser = new ArrayList<>();

    //helper method to add task to a user

    public void addTaskToUser(Task task){
        tasksOwnedByUser.add(task);
        task.setTaskowner(this);
    }

    public void removeTaskFromUser(Task task){
        tasksOwnedByUser.remove(task);
        task.setTaskowner(null);
    }

    @ManyToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="attendants", joinColumns =@JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="task_id"))
    private List<Task> tasksAssignedToUser = new ArrayList<>();

    //helper methods
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


    public List<Role> getRoles()
    {
        return roles;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

    public List<Task> getTasksOwnedByUser()
    {
        return tasksOwnedByUser;
    }

    public void setTasksOwnedByUser(List<Task> tasksOwnedByUser)
    {
        this.tasksOwnedByUser = tasksOwnedByUser;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

//this should be encrypted when security is added
    public void setPassword(String password)
    {
        this.password = password;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, firstName, lastName, email, password);
    }
}

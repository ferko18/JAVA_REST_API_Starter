package com.ferko.taskplanner.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tasks", schema = "public", catalog = "taskplanner")
@Data public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    @Basic
    @Column(name = "description", nullable = false, length = 255)
    private String description;
    @Basic
    @Column(name = "duedate", nullable = false, length = 255)
    @JsonFormat(pattern="MM-dd-yyyy")
    private Date duedate;


    //relationships*********************************************************/

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "task_owners", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties(value ={"usersOwningTask", "hibernateLazyInitializer"})
    private List<User> taskowners;


    @ManyToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name="attendants", joinColumns =@JoinColumn(name="task_id"), inverseJoinColumns = @JoinColumn(name="user_id"))
    private List<User> attendants = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value ={"task", "hibernateLazyInitializer"})
    private List<Agenda> agendas = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value ={"task", "hibernateLazyInitializer"})
    private List<Checklist> checklistitems = new ArrayList<>();

    //helper methods ****************************************************/
    //agenda helper methods
    public void addAgenda(Agenda agenda)
    {
        agendas.add(agenda);
        agenda.setTask(this);
    }


    public void removeAgenda(Agenda agenda)
    {
        agendas.remove(agenda);
        agenda.setTask(null);
    }

    //checklist helper methods
    public void addChecklistItem(Checklist item)
    {
        checklistitems.add(item);
        item.setTask(this);
    }

        public void removeChecklistItem(Checklist item)
    {
        checklistitems.remove(item);
        item.setTask(null);
    }

    //attendant helper methods
    public void addAtendant (User attendant){
        attendants.add(attendant);
        attendant.getTasksAssignedToUser().add(this);
    }

    public void removeAttendant(User attendant)
    {
        attendants.add(attendant);
        attendant.getTasksAssignedToUser().remove(this);
    }

    //taskowner helper methods

    public void addTaskOwner(User owner)
    {
        taskowners.add(owner);
        owner.getUsersOwningTask().add(this);
    }

    public void removeTaskowner(User owner)
    {
        taskowners.remove(owner);
        owner.getUsersOwningTask().remove(this);
    }
}



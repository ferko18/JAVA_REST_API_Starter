package com.ferko.taskplanner.models;

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
    @GeneratedValue
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
    private Date duedate;


    //relationships*********************************************************/
    @ManyToMany(mappedBy = "usersOwningTask")
    private List<User> taskowners;

    @ManyToMany(mappedBy = "tasksAssignedToUser")
    private List<User> attendants = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Agenda> agendas = new ArrayList<>();

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Checklist> checklistitems = new ArrayList<>();

    //helper methods ****************************************************/
    //helper method to add agenda from this class and update the agenda class ( set task)
    public void addAgenda(Agenda agenda)
    {
        agendas.add(agenda);
        agenda.setTask(this);
    }

    //helper method to add agenda from this class and update the agenda class ( set task)
    public void removeAgenda(Agenda agenda)
    {
        agendas.remove(agenda);
        agenda.setTask(null);
    }

    //helper method to add agenda from this class and update the agenda class ( set task)
    public void addChecklistItem(Checklist item)
    {
        checklistitems.add(item);
        item.setTask(this);
    }

    //helper method to add agenda from this class and update the agenda class ( set task)
    public void removeChecklistItem(Checklist item)
    {
        checklistitems.remove(item);
        item.setTask(null);
    }

}



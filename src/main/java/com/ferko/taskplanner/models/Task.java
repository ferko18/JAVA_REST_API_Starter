package com.ferko.taskplanner.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tasks", schema = "public", catalog = "taskplanner")
public class Task
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User taskowner;

    public User getTaskowner()
    {
        return taskowner;
    }

    public void setTaskowner(User taskowner)
    {
        this.taskowner = taskowner;
    }

    public List<Agenda> getAgendas()
    {
        return agendas;
    }

    public void setAgendas(List<Agenda> agendas)
    {
        this.agendas = agendas;
    }

    public List<Checklist> getItems()
    {
        return items;
    }

    public void setItems(List<Checklist> items)
    {
        this.items = items;
    }

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Agenda> agendas = new ArrayList<>();

    //helper method to add agenda from this class and update the agenda class ( set task)
    public void addAgenda (Agenda agenda)
    {
        agendas.add(agenda);
        agenda.setTask(this);
    }
    //helper method to add agenda from this class and update the agenda class ( set task)
    public void removeAgenda (Agenda agenda )
    {
        agendas.remove(agenda);
        agenda.setTask(null);
    }


    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Checklist> items = new ArrayList<>();

    //helper method to add agenda from this class and update the agenda class ( set task)
    public void addChecklistItem (Checklist item)
    {
        items.add(item);
        item.setTask(this);
    }
    //helper method to add agenda from this class and update the agenda class ( set task)
    public void removeChecklistItem (Checklist item )
    {
        items.remove(item);
        item.setTask(null);
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }


    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }


    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }


    public Date getDuedate()
    {
        return duedate;
    }

    public void setDuedate(Date duedate)
    {
        this.duedate = duedate;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(title, task.title) &&
                Objects.equals(description, task.description) &&
                Objects.equals(duedate, task.duedate);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, title, description, duedate);
    }
}

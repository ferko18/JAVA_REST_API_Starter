package com.ferko.taskplanner.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tasks", schema = "public", catalog = "taskplanner")
public class Task
{
    private Integer id;
    private String title;
    private String description;
    private String duedate;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = false, length = 255)
    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Basic
    @Column(name = "duedate", nullable = false, length = 255)
    public String getDuedate()
    {
        return duedate;
    }

    public void setDuedate(String duedate)
    {
        this.duedate = duedate;
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

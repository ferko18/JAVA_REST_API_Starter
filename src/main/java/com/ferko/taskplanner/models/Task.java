package com.ferko.taskplanner.models;

import javax.persistence.*;
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

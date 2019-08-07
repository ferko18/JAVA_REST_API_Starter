package com.ferko.taskplanner.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Checklist
{
    private Integer id;
    private String item;
    private Integer taskId;

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
    @Column(name = "item", nullable = false, length = 25)
    public String getItem()
    {
        return item;
    }

    public void setItem(String item)
    {
        this.item = item;
    }

    @Basic
    @Column(name = "task_id", nullable = false)
    public Integer getTaskId()
    {
        return taskId;
    }

    public void setTaskId(Integer taskId)
    {
        this.taskId = taskId;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Checklist checklist = (Checklist) o;
        return Objects.equals(id, checklist.id) &&
                Objects.equals(item, checklist.item) &&
                Objects.equals(taskId, checklist.taskId);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, item, taskId);
    }
}

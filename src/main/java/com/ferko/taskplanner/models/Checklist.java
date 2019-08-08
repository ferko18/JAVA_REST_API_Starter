package com.ferko.taskplanner.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Checklist
{
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "item", nullable = false, length = 25)
    private String item;


    @Basic
    @Column(name = "task_id", nullable = false)
    private Integer taskId;

    //this can be left out since we have the task object here
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task ;

    public Task getTask()
    {
        return task;
    }

    public void setTask(Task task)
    {
        this.task = task;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }


    public String getItem()
    {
        return item;
    }

    public void setItem(String item)
    {
        this.item = item;
    }


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

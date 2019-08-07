package com.ferko.taskplanner.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "agendas", schema = "public", catalog = "taskplanner")
public class Agenda
{
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    //this can be left out since we have the task object here
    @Basic
    @Column(name = "task_id", nullable = false)
    private Integer taskId;
    private String agenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

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


    public Integer getTaskId()
    {
        return taskId;
    }

    public void setTaskId(Integer taskId)
    {
        this.taskId = taskId;
    }

    @Basic
    @Column(name = "agenda", nullable = false, length = 255)
    public String getAgenda()
    {
        return agenda;
    }

    public void setAgenda(String agenda)
    {
        this.agenda = agenda;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agenda agenda1 = (Agenda) o;
        return Objects.equals(id, agenda1.id) &&
                Objects.equals(taskId, agenda1.taskId) &&
                Objects.equals(agenda, agenda1.agenda);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, taskId, agenda);
    }
}

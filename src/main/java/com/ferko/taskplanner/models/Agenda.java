package com.ferko.taskplanner.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name = "agendas", schema = "public", catalog = "taskplanner")
@AllArgsConstructor
@NoArgsConstructor
@Data public class Agenda
{
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "agenda", nullable = false, length = 255)
    private String agenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    public Agenda(String agenda)
    {
        this.agenda=agenda;
    }
}

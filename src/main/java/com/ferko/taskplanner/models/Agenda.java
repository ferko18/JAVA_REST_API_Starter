package com.ferko.taskplanner.models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "agendas", schema = "public", catalog = "taskplanner")
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
}

package com.ferko.taskplanner.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data public class Checklist
{
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "item", nullable = false, length = 25)
    private String item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task ;

}

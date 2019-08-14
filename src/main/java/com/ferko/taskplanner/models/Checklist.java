package com.ferko.taskplanner.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "checklist", schema = "public", catalog = "taskplanner")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checklist
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Basic
    @Column(name = "item", nullable = false, length = 25)
    private String item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private Task task ;

    //this is needed to update this class from Task
    public Checklist(String item)
    {
       this.item= item;
    }
}

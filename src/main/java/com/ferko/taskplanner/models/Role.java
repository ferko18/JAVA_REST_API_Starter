package com.ferko.taskplanner.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "roles", schema = "public", catalog = "taskplanner")
public class Role
{
    private Integer id;
    private String name;

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
    @Column(name = "name", nullable = false, length = 255)
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id) &&
                Objects.equals(name, role.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, name);
    }
}

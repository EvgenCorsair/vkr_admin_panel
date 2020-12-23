package ru.evgen.adminpanel.entity;

import javax.persistence.*;

@Table(name = "Action")
@Entity(name = "Action")
public class Action {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

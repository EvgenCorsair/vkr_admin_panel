package ru.evgen.adminpanel.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "Event")
@Entity(name = "Event")
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

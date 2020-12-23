package ru.evgen.adminpanel.entity;

import javax.persistence.*;
import java.util.List;

@Table(name = "Device")
@Entity(name = "Device")
public class Device {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;


    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    @Column
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

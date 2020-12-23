package ru.evgen.adminpanel.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Entity
@Table(name = "roles")
public class Role {
    public Role() {
    }

    public Role(UUID id,String name) {
        this.id = id;
        this.name = name;
    }

    @Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;

    @Column(nullable=false, unique=true)
    private String name;

    @ManyToMany(mappedBy="roles", fetch = FetchType.EAGER)
    public Set<User> users = new HashSet<>();


    public UUID getRoleId() {
        return id;
    }

    public void setRoleId(UUID roleId) {
        this.id = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

package edu.ib.repo.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "groups_database")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_group;

    private String name;
    private int picture_id;

    @ManyToMany(cascade=CascadeType.MERGE)
    @JoinTable(
            name = "groups_users",
            joinColumns = @JoinColumn(name = "id_group"),
            inverseJoinColumns =@JoinColumn(name = "id_user")
    )
    private Set<User> users;

    public Group() {
    }

    public Group(String name, int picture_id, Set<User> users) {
        this.name = name;
        this.picture_id = picture_id;
        this.users = users;
    }

    public Long getId_group() {
        return id_group;
    }

    public void setId_group(Long id_group) {
        this.id_group = id_group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(int picture_id) {
        this.picture_id = picture_id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

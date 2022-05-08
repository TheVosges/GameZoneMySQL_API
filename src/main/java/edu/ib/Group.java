package edu.ib;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_group;

    private String name;
    private Long picture_id;

    @ManyToMany
    @JoinTable(
            name = "groups_users",
            joinColumns = @JoinColumn(name = "id_group"),
            inverseJoinColumns =@JoinColumn(name = "id_user")
    )
    private Set<User> users;

    public Group(String name, Long picture_id, Set<User> users) {
        this.name = name;
        this.picture_id = picture_id;
        this.users = users;
    }

    public Group() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id_group.equals(group.id_group) && name.equals(group.name) && picture_id.equals(group.picture_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_group, name, picture_id);
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

    public Long getPicture_id() {
        return picture_id;
    }

    public void setPicture_id(Long picture_id) {
        this.picture_id = picture_id;
    }
}

package edu.ib.repo.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    private String email;
    private String password;

//    @OneToOne(cascade = CascadeType.ALL)
//    @PrimaryKeyJoinColumn
//    private UserGame userGame;

    @Transient
    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<Group> groups;

    @Transient
    @ManyToMany(cascade = CascadeType.MERGE)
    private Set<Game> games;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name="user_followers")
//    private Set<Followers> followers;


    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.groups = new HashSet<>();
        this.games = new HashSet<>();
    }

    public User() {
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id_user.equals(user.id_user) && email.equals(user.email) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_user, email, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

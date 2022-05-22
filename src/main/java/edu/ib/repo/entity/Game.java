package edu.ib.repo.entity;


import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_game;
    private String name;

    public Game(String name){
        this.name = name;
    }

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "users_games",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns =@JoinColumn(name = "id_game")
    )
    private Set<User> users;


    public Game() {
    }

    public Long getId_game() {
        return id_game;
    }

    public void setId_game(Long id_game) {
        this.id_game = id_game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return id_game.equals(game.id_game) && name.equals(game.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_game, name);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id_game=" + id_game +
                ", name='" + name + '\'' +
                '}';
    }
}

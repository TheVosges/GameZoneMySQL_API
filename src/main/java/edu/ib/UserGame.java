package edu.ib;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
@Table(name = "users_games")
@Entity
public class UserGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Type(type = "list-array")
    @Column(
            name = "sensor_long_values",
            columnDefinition = "bigint[]"
    )
    private List<Long> games;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;


    public UserGame(List<Long> games) {
        this.games = games;
    }

    public UserGame() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGame userGame = (UserGame) o;
        return id_user.equals(userGame.id_user) && games.equals(userGame.games);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_user, games);
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public List<Long> getId_game() {
        return games;
    }

    public void setId_game(List<Long> games) {
        this.games = games;
    }
}

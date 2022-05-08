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
@Table(name = "followers")
@Entity
public class Followers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Type(type = "list-array")
    @Column(
            name = "sensor_long_values",
            columnDefinition = "bigint[]"
    )
    private List<Long> followers;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;


    public Followers(List<Long> followers) {
        this.followers = followers;
    }

    public Followers() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Followers followers = (Followers) o;
        return id_user.equals(followers.id_user) && followers.equals(followers.followers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_user, followers);
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public List<Long> getId_game() {
        return followers;
    }

    public void setId_game(List<Long> followers) {
        this.followers = followers;
    }
}

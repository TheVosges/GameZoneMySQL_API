package edu.ib.repo;

import edu.ib.repo.entity.Game;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GameRepo extends CrudRepository<Game, Long> {
}

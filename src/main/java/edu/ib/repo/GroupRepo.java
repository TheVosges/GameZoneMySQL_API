package edu.ib.repo;

import edu.ib.repo.entity.Group;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepo extends CrudRepository<Group, Long> {
}

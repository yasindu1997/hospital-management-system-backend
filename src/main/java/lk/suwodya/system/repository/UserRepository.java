package lk.suwodya.system.repository;

import lk.suwodya.system.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}

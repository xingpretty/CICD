package xing.start.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xing.start.game.domain.User;


public interface UserRepository extends JpaRepository<User,Long> {
}

package ro.siit.pms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.siit.pms.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    User findByName(String name);
}

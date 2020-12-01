package roberts.inventory.data.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import roberts.inventory.data.UsersEntity;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, String> {
    UsersEntity findUsersEntityByUsername(String username);
    boolean existsByUsername(String username);

}

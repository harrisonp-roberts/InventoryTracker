package roberts.inventory.data.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import roberts.inventory.data.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findUsersEntityByUsername(String username);
    UserEntity getUserEntityByUsername(String username);
    UserEntity findUserEntitiesByUsernameAndPassword(String username, String password);
    boolean existsByUsername(String username);

}

package roberts.inventory.data.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import roberts.inventory.data.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
    UserEntity findUsersEntityByUsername(String username);
    UserEntity getUserEntityByUsername(String username);
    UserEntity findUserEntityByUsernameAndPassword(String username, String password);
    boolean existsUserEntityByToken(String token);
    boolean existsByUsername(String username);

    boolean existsByPassword(String password);

}

package roberts.inventory.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import roberts.inventory.data.UserEntity;
import roberts.inventory.data.repositories.UserRepository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Component
@Service
public class PasswordService {
    private UserRepository userRepo;

    @Autowired
    public PasswordService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    String getHashedPassword(UserEntity user) throws NoSuchAlgorithmException {
        final var unauthorizedAccount = userRepo.getUserEntityByUsername(user.getUsername());
        final var salt = unauthorizedAccount.getSalt();
        var password = user.getPassword();

        password = password + salt;
        return hash(password);
    }

    UserEntity saltAndHash(UserEntity user) throws NoSuchAlgorithmException {
        var password = user.getPassword();
        final var salt = UUID.randomUUID().toString();
        password = salt + password;

        password = hash(password);

        user.setPassword(password);
        user.setSalt(salt);

        return user;
    }

    /**
     * Used to hash passwords that already include the salt
     *
     * @param toHash salted password to be hashed
     * @return hashed password
     * @throws NoSuchAlgorithmException If hashing algorithm is incorrectly specified
     */
    private String hash(String toHash) throws NoSuchAlgorithmException {
        //Class that ingests strings, and hashes them
        var digester = MessageDigest.getInstance("SHA-256");
        digester.update(toHash.getBytes());
        return new String(digester.digest());
    }
}

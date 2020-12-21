package roberts.inventory.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import roberts.inventory.data.UserEntity;
import roberts.inventory.data.repositories.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@RequestMapping("/")
@RestController
public class UserService {
    private UserRepository userRepo;
    private PasswordService passwordService;

    @Autowired
    UserService(UserRepository userRepo, PasswordService passwordService) {
        this.userRepo = userRepo;
        this.passwordService = passwordService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/plain")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("REQUEST OK");
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "text/plain", consumes = "application/json")
    public ResponseEntity<String> register(@RequestBody UserEntity user) {
        final var username = user.getUsername();
        final var doesExist = userRepo.existsByUsername(username);

        if (!doesExist) {
            try {
                user = passwordService.saltAndHash(user);
                final var token = UUID.randomUUID().toString();
                user.setToken(token);
                userRepo.save(user);

                return ResponseEntity.ok(token);
            } catch (NoSuchAlgorithmException e) {
                return ResponseEntity.status(500).body("An internal server error has occurred");
            }
        } else {
            return ResponseEntity.status(401).body("User Already Exists");
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "text/plain", consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody UserEntity user) {
        final var username = user.getUsername();
        final var exists = userRepo.existsByUsername(username);

        if(exists) {
            try {
                user.setPassword(passwordService.getHashedPassword(user));
            } catch (NoSuchAlgorithmException e) {
                0return ResponseEntity.status(500).body("An internal server error occurred");
            }

            final var authorizedUser = userRepo.findUserEntityByUsernameAndPassword(user.getUsername(), user.getPassword());

            if(authorizedUser != null) {
                final var token = UUID.randomUUID().toString();
                authorizedUser.setToken(token);
                userRepo.save(authorizedUser);

                return ResponseEntity.ok(token);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("INVALID CREDENTIALS");
            }
        } else {
            return ResponseEntity.status(401).body("A User with that username does not exist");
        }
    }
}

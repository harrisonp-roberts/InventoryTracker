package roberts.inventory.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import roberts.inventory.data.UsersEntity;
import roberts.inventory.data.repositories.UserRepository;

@RequestMapping("/")
@RestController
public class UserService {
    private UserRepository userRepo;

    @Autowired
    UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "text/plain")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("REQUEST OK");
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST, produces = "text/plain")
    public ResponseEntity<String> create(@RequestBody UsersEntity user) {
        final var username = user.getUsername();
        final var doesExist = userRepo.existsByUsername(username);

        if(!doesExist) {
            return ResponseEntity.ok("");
        } else {
            return ResponseEntity.status(500).body("An Account has Already Been Created with that Username");
        }
    }
}

package HackerRank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//public class UserRestController {
//    private final UserRepository userRepository;
//
//    public UserRestController(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @GetMapping("/users/")
//    public List<User> getUsers(@PathVariable String name) {
//        List<UserDto> allUsers = userRepository.findAll();
//
//        if (name != null) {
//            // Filter users by name
//            List<UserDto> res = allUsers.stream()
//                    .filter(user -> user.getName().equals(name))
//                    .collect(Collectors.toList());
//            return ResponseEntity.ok(res);
//        }
//
//        return ResponseEntity.ok(allUsers);
//    }
//}

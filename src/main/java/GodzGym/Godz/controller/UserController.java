package GodzGym.Godz.controller;

import GodzGym.Godz.domain.User;
import GodzGym.Godz.response.ResponseHandler;
import GodzGym.Godz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User newUser = userService.createUser(user);
        return ResponseHandler.generateResponse("User created successfully!");
    }

    @GetMapping("/users")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();

    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Long userId) {
        return new ResponseEntity<>(userService.getUserById(userId), HttpStatus.OK);
    }

    @DeleteMapping("users/{userId}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseHandler.generateResponse("User deleted successfully!");
    }

    @PostMapping("/users/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user){
        return userService.updateUser(userId, user);
    }
}

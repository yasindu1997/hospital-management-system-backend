package lk.suwodya.system.controller;

import lk.suwodya.system.entity.User;
import lk.suwodya.system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addUser(@RequestBody User user) {
        if (!userRepository.existsById(user.getUserName())) {
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{userName}")
    public ResponseEntity deleteUser(@PathVariable("userName") String userName) {
        if (userRepository.existsById(userName)) {
            userRepository.deleteById(userName);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{userName}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUser(@PathVariable("userName") String userName, @RequestBody User user) {
        if (userRepository.existsById(userName)) {
            userRepository.save(user);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers() {
        ArrayList<User> all = new ArrayList<>();
        for (User u : userRepository.findAll()) {
            all.add(u);
        }
        return all;
    }

    @GetMapping(value = "/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("userName") String userName) {
        if (userRepository.existsById(userName)) {
            return userRepository.findById(userName).get();
        }
        return null;
    }
}

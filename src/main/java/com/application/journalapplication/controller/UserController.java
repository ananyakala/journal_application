package com.application.journalapplication.controller;
import com.application.journalapplication.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.application.journalapplication.entity.User;

// import java.util.HashMap;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.UserEntry(User);
    }

    @PutMapping("/user")
    public ResponseEntity<?> updateUser(@RequestBody User user){
        User userInDb = userService.findbyUserName(user.getUsername());
        if(userInDb!=null)
        {
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    
    
}

}

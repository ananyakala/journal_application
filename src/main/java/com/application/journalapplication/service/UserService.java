package com.application.journalapplication.service;

import com.application.journalapplication.entity.User;
import com.application.journalapplication.repository.UserEntryRepo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    // this is where the business logic will be written
    @Autowired
    private UserEntryRepo userRepo;

    public void saveEntry(User user) {
        userRepo.save(user);
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public Optional<User> findID(ObjectId Id) {
        return userRepo.findById(Id);
    }

    public void deleteID(ObjectId Id) {
        userRepo.deleteById(Id);
    }

    public User findUserName(String username) {
        return userRepo.findbyusername(username);

    }

}

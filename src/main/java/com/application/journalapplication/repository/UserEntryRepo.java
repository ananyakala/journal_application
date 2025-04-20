package com.application.journalapplication.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.application.journalapplication.entity.User;

public interface UserEntryRepo extends MongoRepository<User, ObjectId> {
    User findbyusername(String username);

}

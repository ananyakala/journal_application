package com.application.journalapplication.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.*;
import com.mongodb.lang.NonNull;

import lombok.Data;
// import lombok.Getter;
// import lombok.Setter;

// import java.time.LocalDateTime;

@Document(collection = "users")
@Data
public class User {

    @Id // Mapping it as a primary key
    private ObjectId id;

    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;

    @DBRef // creating the journal entries reference for the user
    private List<JournalEntry> journal_entries = new ArrayList<>();

}

// creating user functionality

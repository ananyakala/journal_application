package com.application.journalapplication.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
// import lombok.Getter;
// import lombok.Setter;

import java.time.LocalDateTime;

@Document(collection = "journal_entries")
@Data
public class JournalEntry {

    @Id // Mapping it as a primary key

    private ObjectId id;

    private LocalDateTime date;

    private String title;
}

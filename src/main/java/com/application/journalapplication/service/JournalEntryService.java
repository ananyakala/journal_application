package com.application.journalapplication.service;

import com.application.journalapplication.entity.JournalEntry;
import com.application.journalapplication.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
//    this is where the business logic will be written
    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> findID(ObjectId Id){
        return journalEntryRepo.findById(Id);
    }

    public void deleteID(ObjectId Id){
        journalEntryRepo.deleteById(Id);
    }

}

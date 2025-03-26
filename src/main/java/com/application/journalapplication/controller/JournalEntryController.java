package com.application.journalapplication.controller;

import com.application.journalapplication.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

import com.application.journalapplication.entity.JournalEntry;

// import java.util.HashMap;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService; // *instance of journalEntryservice is created and it is injected
                                                     // in the class*//

    // private Map<String, JournalEntry> JE = new HashMap<>();
    // JournalEntry is the datatype here

    @GetMapping("id") // get in postman
    public ResponseEntity<?> getAll() {
        List<JournalEntry> all = journalEntryService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<JournalEntry> CreateEntry(@RequestBody JournalEntry entry) {
        try {

            entry.setDate(LocalDateTime.now());
            // JE.put(entry.getId(), entry);
            journalEntryService.saveEntry(entry);
            return new ResponseEntity<>(entry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(entry, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getEntry(@PathVariable ObjectId myId) {
        // return JE.get(myId);
        // return journalEntryService.findID(myId).orElse(null);
        Optional<JournalEntry> journalEntry = journalEntryService.findID(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK); // parameters : object & httpCode
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteEntry(@PathVariable ObjectId myId) { // "?" this basically means that it's not
                                                                        // necessary to give an object of the class, it
                                                                        // can be used for other classes also.
        // return JE.remove(myId);
        journalEntryService.deleteID(myId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
        // return JE.put(id, entry);
        JournalEntry old = journalEntryService.findID(id).orElse(null); // find the
        // id to update
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && newEntry.getTitle().equals("") ? newEntry.getTitle()
                    : old.getTitle());
            old.setContent(newEntry.getContent() != null &&
                    newEntry.getContent().equals("") ? newEntry.getContent()
                            : old.getContent());
        }
        // entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(old);
        return old;
    }

    // @PutMapping("/{id}")
    // public JournalEntry updateEntry(@PathVariable ObjectId id, @RequestBody
    // JournalEntry newEntry) {
    // JournalEntry old = journalEntryService.findID(id).orElse(null);
    // if (old != null) {
    // if (newEntry.getTitle() != null && !newEntry.getTitle().equals("")) {
    // old.setTitle(newEntry.getTitle());
    // }
    // if (newEntry.getContent() != null && !newEntry.getContent().equals("")) {
    // old.setContent(newEntry.getContent());
    // }
    // // You might want to update the timestamp when entries are modified
    // // old.setDate(LocalDateTime.now());
    // journalEntryService.saveEntry(old);
    // return old;
    // } else {
    // // Handle case where entry doesn't exist
    // return null; // Or throw an exception
    // }
    // }

}

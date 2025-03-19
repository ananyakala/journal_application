package com.application.journalapplication.controller;
import com.application.journalapplication.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

import com.application.journalapplication.entity.JournalEntry;

import java.util.HashMap;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService; //*instance of journalEntryservice is created and it is injected in the class*//



    private Map<String, JournalEntry> JE=new HashMap<>();
    // JournalEntry is the datatype here

    @GetMapping("id") //get in postman
    public List<JournalEntry> getAll(){
//        return new ArrayList<>(JE.values());
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry CreateEntry(@RequestBody JournalEntry entry){
        entry.setDate(LocalDateTime.now());
//        JE.put(entry.getId(), entry);
        journalEntryService.saveEntry(entry);
        return entry;
    }

    @GetMapping("id/{myId}")
    public JournalEntry getEntry(@PathVariable ObjectId myId){
//        return JE.get(myId);
        return journalEntryService.findID(myId).orElse(null);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteEntry(@PathVariable ObjectId myId){
//        return JE.remove(myId);
        journalEntryService.deleteID(myId);
        return true;
    }

    @PutMapping("id/{id}")
    public JournalEntry updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
//        return JE.put(id, entry);
        JournalEntry old= journalEntryService.findID(id).orElse(null); //find the id to update
        if(old !=null)
        {
            old.setTitle(newEntry.getTitle()!=null&&newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!=null && newEntry.getContent().equals("") ? newEntry.getContent() :  old.getContent());
        }
//        entry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(old);
        return old;
    }

}

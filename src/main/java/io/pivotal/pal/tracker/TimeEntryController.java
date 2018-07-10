package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class TimeEntryController {

    TimeEntryRepository repository;

    public TimeEntryController(@Autowired TimeEntryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable long id) {
        TimeEntry entry = repository.find(id);
        ResponseEntity<TimeEntry> response = null;
        if (entry != null) {
            response = ResponseEntity.status(HttpStatus.OK).body(entry);
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(entry);
        }
        return response;
    }

    @GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
       List<TimeEntry> list = repository.list();
       return ResponseEntity.ok(list);
    }

    @PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        Logger.getAnonymousLogger().info(timeEntryToCreate.toString());

        TimeEntry entry = repository.create(timeEntryToCreate);
        ResponseEntity<TimeEntry> response =
                ResponseEntity.status(HttpStatus.CREATED).body(entry);
        return response;
    }


    @PutMapping("/time-entries/{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody TimeEntry entry) {
        TimeEntry timeEntry = repository.update(id, entry);

        ResponseEntity<TimeEntry> response = null;
        if (timeEntry != null) {
            response = ResponseEntity.status(HttpStatus.OK).body(entry);
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(entry);
        }

        return response;
    }

    @DeleteMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable long id) {
        TimeEntry entry = repository.find(id);
        repository.delete(id);
        ResponseEntity<TimeEntry> response =
                ResponseEntity.status(HttpStatus.NO_CONTENT).body(entry);

        return response;
    }

}

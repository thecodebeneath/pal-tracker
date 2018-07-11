package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;
    private CounterService counterService;
    private GaugeService gaugeService;

    public TimeEntryController(
            TimeEntryRepository timeEntryRepository,
            CounterService counterService,
            GaugeService gaugeService) {
        this.timeEntryRepository = timeEntryRepository;
        this.counterService = counterService;
        this.gaugeService = gaugeService;
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        counterService.increment("Time.Listed");
        return new ResponseEntity<>(timeEntryRepository.list(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        counterService.increment("Time.Created");
        gaugeService.submit("Time.NumberOfEntries", timeEntryRepository.list().size());
        return new ResponseEntity<TimeEntry>(timeEntryRepository.create(timeEntryToCreate),HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<TimeEntry> read (@PathVariable long id) {
       TimeEntry timeEntry = timeEntryRepository.find(id);
       if (timeEntry == null) {
           return new ResponseEntity<TimeEntry>(HttpStatus.NOT_FOUND);
       } else {
           counterService.increment("Time.Read");
           return new ResponseEntity<TimeEntry>(timeEntry, HttpStatus.OK);
       }
    }

    @PutMapping("{id}")
    public ResponseEntity<TimeEntry> update(@PathVariable Long id, @RequestBody TimeEntry timeEntry) {
        TimeEntry updatedTimeEntry = timeEntryRepository.update(id, timeEntry);
        if (updatedTimeEntry != null) {
            counterService.increment("Time.Updated");
            return new ResponseEntity<>(updatedTimeEntry, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TimeEntry> delete (@PathVariable long id) {
        timeEntryRepository.delete(id);
        counterService.increment("Time.Deleted");
        gaugeService.submit("Time.NumberOfEntries", timeEntryRepository.list().size());
        return new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
    }
}


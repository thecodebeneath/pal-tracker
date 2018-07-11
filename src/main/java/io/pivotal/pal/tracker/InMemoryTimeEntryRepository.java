package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    Map<Long, TimeEntry> repo = new HashMap<>();
    private Long lastIdUsed =  0L;

    public TimeEntry create(TimeEntry timeEntry) {
        if (timeEntry.getId() == 0) {
            timeEntry.setId(++lastIdUsed);
        }
        repo.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    public void delete(Long id) {
        repo.remove(id);
    }

    public TimeEntry find(Long id) {
        return repo.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<TimeEntry>(repo.values());
    }

    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry find = repo.get(id);
        if (find == null) {
            return null;
        }
        timeEntry.setId(id);
        repo.put(id, timeEntry);

        return timeEntry;
    }
}

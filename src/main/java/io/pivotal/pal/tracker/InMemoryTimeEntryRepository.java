package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private HashMap<Long, TimeEntry> timeEntryList;

    public InMemoryTimeEntryRepository() {
        timeEntryList = new HashMap<Long, TimeEntry> ();
    }

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        long newId = (long)timeEntryList.size()+1;
        TimeEntry newTimeEntry = new TimeEntry(newId, timeEntry.getProjectId(),timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        timeEntryList.put(newId, newTimeEntry);
        return find(newId);
    }

    @Override
    public TimeEntry find(Long id) {
        return timeEntryList.get(id);
    }

    @Override
    public ArrayList<TimeEntry> list() {
        return new ArrayList<TimeEntry>(timeEntryList.values());
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) {
        TimeEntry updatedEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );

        timeEntryList.replace(id, updatedEntry);
        return updatedEntry;
    }

    @Override
    public void delete(Long id) {
        timeEntryList.remove(id);
    }
}

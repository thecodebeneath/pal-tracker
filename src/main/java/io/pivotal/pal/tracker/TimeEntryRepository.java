package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

    public TimeEntry create(TimeEntry timeEntry);

    public void delete(long id);

    public TimeEntry find(long i);

    public List<TimeEntry> list();

    public TimeEntry update(long id, TimeEntry timeEntry);

}
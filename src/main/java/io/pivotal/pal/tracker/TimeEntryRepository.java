package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

    public TimeEntry create(TimeEntry timeEntry);

    public void delete(Long id);

    public TimeEntry find(Long i);

    public List<TimeEntry> list();

    public TimeEntry update(Long id, TimeEntry timeEntry);

}

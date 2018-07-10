package io.pivotal.pal.tracker;

import org.apache.tomcat.jni.Local;

import java.time.LocalDate;

public class TimeEntry {
    private long id = Long.MIN_VALUE;
    private long projectId;
    private long userId;
    private LocalDate date;
    private int hours;

    public TimeEntry() {
    }

    public TimeEntry(long projectId, long userId, LocalDate d, int hours) {
        this.projectId = projectId;
        this.userId = userId;
        this.date = d;
        this.hours = hours;
    }

    public TimeEntry(long id, long projectId, long userId, LocalDate d, int hours) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.date = d;
        this.hours = hours;
    }

    public void setId(long newId) {
        id = newId;
    }

    public long getId() {
        return id;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public boolean equals(Object o) {
        if (o instanceof TimeEntry) {
            TimeEntry te = (TimeEntry) o;
            if (this.id == te.id &&
                    this.projectId == te.projectId &&
                    this.userId == te.userId &&
                    this.date.equals(te.date) &&
                    this.hours == te.hours) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

//    public int hashCode() {
//
//    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("id: ");
        sb.append(id);
        sb.append("projectId: ");
        sb.append(projectId);
        return sb.toString();
    }
}

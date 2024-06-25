package com.rsvpagentprocessing.rsvpagentprocessing.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;

@Entity
@Builder()
public class RSVP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String date;
    private String time;
    private String level;
    private String action;
    private String message;

    public RSVP() {
    }

    public RSVP(Integer id, String date, String time, String level, String action, String message) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.level = level;
        this.action = action;
        this.message = message;
    }

    public RSVP(String s) {
    }

    public RSVP(RSVP rsvp) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RSVP{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", level='" + level + '\'' +
                ", action='" + action + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}

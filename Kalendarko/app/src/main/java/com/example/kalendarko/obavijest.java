package com.example.kalendarko;

public class obavijest {
    private String date, event, description;
    private int id, repeat;

    public obavijest(int id, String date, String event, String description, int repeat) {
        this.id = id;
        this.date = date;
        this.event = event;
        this.description = description;
        this.repeat = repeat;
    }

    public obavijest(){};

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }
}

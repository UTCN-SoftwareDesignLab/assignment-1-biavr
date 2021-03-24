package model;

import java.util.Date;

public class Action {
    private final Long id;
    private final String type;
    private final Date date;

    public Action(Long id, String type, Date date) {
        this.id = id;
        this.type = type;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public Long getId() {
        return id;
    }
}

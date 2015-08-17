package models.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.utils.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by shane on 8/17/15.
 */
@Entity
public class EventType extends BaseModel {

    public static Finder<Long, EventType> find = new Finder<>(EventType.class);

    @JsonIgnore
    @ManyToOne
    public Type type;

    @JsonIgnore
    @ManyToOne
    public Event event;

    public EventType(Type type, Event event) {
        this.type = type;
        this.event = event;
    }
}

package models.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.utils.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by shane on 8/9/15.
 */
@Entity
public class EventPlace extends BaseModel {

    public static Finder<Long, EventPlace> find = new Finder<>(EventPlace.class);

    @JsonIgnore
    @ManyToOne
    public Event event;

    @JsonIgnore
    @ManyToOne
    public Place place;

    public String description;
}

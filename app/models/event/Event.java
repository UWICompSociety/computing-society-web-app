package models.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.utils.BaseEntity;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by shane on 8/9/15.
 */
@Entity
public class Event extends BaseEntity {

    public static Finder<Long, Event> find = new Finder<>(Event.class);

    @Constraints.Required
    public String name;

    @Formats.DateTime(pattern = "dd/MM/yyyy")
    public Date startTime;

    @Formats.DateTime(pattern = "dd/MM/yyyy")
    public Date endTime;

    // change to enum -> 'planning', 'started', 'cancelled', etc
//    public String status;

    public int rating;

    // OneToMany vs OneToOne in the off chance it moves or is large enough to span multiple locations
    @JsonIgnore
    @OneToMany(mappedBy = "event")
    public List<EventPlace> places;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    public List<EventUser> attendees;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    public List<EventType> types;
}

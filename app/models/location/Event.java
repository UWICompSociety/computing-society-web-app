package models.location;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import controllers.utils.EntityController;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

/**
 * Created by shane on 8/9/15.
 */
@Entity
public class Event extends EntityController {

    public static Model.Finder<Long, Event> find = new Model.Finder<>(Event.class);

    @Constraints.Required
    public String name;

    public Date startTime;
    public Date endTime;

    // change to enum -> 'planning', 'started', 'cancelled', etc
    public String status;

    public int rating;

    // OneToMany vs OneToOne in the off chance it moves or is large enough to span multiple locations
    @JsonIgnore
    @OneToMany(mappedBy = "event")
    public List<EventPlace> places;

    @JsonIgnore
    @OneToMany(mappedBy = "event")
    public List<EventUser> attendees;
}

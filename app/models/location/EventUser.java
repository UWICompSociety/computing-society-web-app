package models.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.user_management.User;
import models.utils.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by shane on 8/9/15.
 */
@Entity
public class EventUser extends BaseModel {

    public static Finder<Long, EventUser> find = new Finder<>(EventUser.class);

    @JsonIgnore
    @ManyToOne
    public User user;

    @JsonIgnore
    @ManyToOne
    public Event event;

    public String description;
}

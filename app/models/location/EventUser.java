package models.location;

import models.user_management.User;
import models.utils.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by shane on 8/9/15.
 */
@Entity
public class EventUser extends BaseModel {

    @ManyToOne
    public User user;

    @ManyToOne
    public Event event;

    public String description;
}

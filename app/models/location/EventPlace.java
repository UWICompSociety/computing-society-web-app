package models.location;

import models.utils.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by shane on 8/9/15.
 */
@Entity
public class EventPlace extends BaseModel {

    @ManyToOne
    public Event event;

    @ManyToOne
    public Place place;

    public String description;
}

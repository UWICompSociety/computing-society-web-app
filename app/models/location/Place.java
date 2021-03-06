package models.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.event.EventPlace;
import models.utils.BaseEntity;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

/**
 * Created by shane on 8/9/15.
 */
@Entity
public class Place extends BaseEntity {

    public static Finder<Long, Place> find = new Finder<>(Place.class);

    @Constraints.Required
    public String name;



    @JsonIgnore
    @OneToOne
    public Address address;

    @JsonIgnore
    @OneToMany
    List<EventPlace> events;
}

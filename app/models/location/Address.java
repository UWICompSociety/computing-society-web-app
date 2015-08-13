package models.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.user_management.Profile;
import models.utils.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by shane on 8/8/15.
 */
@Entity
public class Address extends BaseEntity {

    public static Finder<Long, Address> find = new Finder<>(Address.class);

    @JsonIgnore
    @OneToOne
    public Coordinate coordinate;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    public Profile profile;

    @JsonIgnore
    @OneToOne(mappedBy = "address")
    public Place place;
}

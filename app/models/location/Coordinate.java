package models.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.utils.BaseEntity;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 * Created by shane on 8/8/15.
 */
@Entity
public class Coordinate extends BaseEntity {

    public static Finder<Long, Coordinate> find = new Finder<>(Coordinate.class);

    @Constraints.Required
    public double latitude;

    @Constraints.Required
    public double longitude;

    @JsonIgnore
    @OneToOne(mappedBy = "coordinate")
    public Address address;

    public Coordinate(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}

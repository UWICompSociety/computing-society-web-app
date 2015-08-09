package models.location;

import controllers.utils.EntityController;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Constraint;
import java.util.List;

/**
 * Created by shane on 8/9/15.
 */
@Entity
public class Place extends EntityController {

    @Constraints.Required
    public String name;

    public String shortName;

    @OneToOne
    public Address address;

    @OneToMany
    List<EventPlace> events;
}

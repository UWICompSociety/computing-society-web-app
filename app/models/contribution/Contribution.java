package models.contribution;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.user_management.Profile;
import models.utils.BaseEntity;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by shane on 8/9/15.
 */
@Entity
public class Contribution extends BaseEntity {

    public static Finder<Long, Contribution> find = new Finder<>(Contribution.class);

    @Constraints.Required
    public int amount;

    @JsonIgnore
    @ManyToOne
    public Profile profile;

    public Contribution(int amount) {
        this.amount = amount;
    }
}

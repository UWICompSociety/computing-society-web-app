package models.user_management;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.utils.BaseEntity;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by shane on 8/6/15.
 */
@Entity
public class Role extends BaseEntity {

    @Constraints.Required
    public String name;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    public List<RoleUser> users;
}

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

    public static Finder<Long, Role> find = new Finder<>(Role.class);

    @Constraints.Required
    public String name;

    public String description;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    public List<RoleUser> users;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role(String name) {
        this(name, null);
    }
}

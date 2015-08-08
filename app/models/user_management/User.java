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
public class User extends BaseEntity {

    @Constraints.Required
    public String username;

    @Constraints.Required
    public String email;

    @Constraints.Required
    public String password;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    public List<RoleUser> roles;

    public User() {

        this.deleted = false;
    }
}

package models.user_management;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.utils.BaseEntity;
import models.utils.BaseModel;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by shane on 8/8/15.
 */
@Entity
public class RoleUser extends BaseModel {

    public static Finder<Long, RoleUser> find = new Finder<>(RoleUser.class);

    @JsonIgnore
    @ManyToOne
    public Role role;

    @JsonIgnore
    @ManyToOne
    public User user;

    public RoleUser() {}

    public RoleUser(User addUser, Role addRole) {
        user = addUser;
        role = addRole;
    }

    public static RoleUser newIntance(){
        return new RoleUser();
    }

    public static RoleUser createRelation(User user, Role role) {
        RoleUser relation = new RoleUser(user, role);
        relation.save();
        return relation;
    }
}

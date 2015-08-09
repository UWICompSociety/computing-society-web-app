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

    @JsonIgnore
    @ManyToOne
    public Role role;

    @JsonIgnore
    @ManyToOne
    public User user;
}

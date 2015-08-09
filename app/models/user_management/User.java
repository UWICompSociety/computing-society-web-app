package models.user_management;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.utils.BaseEntity;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shane on 8/6/15.
 */
@Entity
public class User extends BaseEntity {

    public static Finder<Long, User> find = new Finder<>(User.class);

    public String username;

    @Constraints.Required
    public String email;

    public String password;

    public boolean verified = false;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    public Profile profile;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    public List<RoleUser> roles;

    public User(String email, String username, String password, boolean verified) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.verified = verified;
    }

    public static User createMember(String email) {
        return new User(email, null, null, false);
    }

    public static boolean isEmailTaken(String email) {
        return User.find.where().eq("email", email).findUnique() != null;
    }

    public static User register(String email, String username, String password) {
        return new User(email, username, password, false);
    }

    public List<Role> getAllRoles() {
        List<RoleUser> roleUsers = this.roles;
        List<Role> roles = new ArrayList<>();

//        for (RoleUser roleUser: roleUsers) {
//            roles.add(roleUser.role);
//        }

        roles.addAll(roleUsers.stream().map(roleUser -> roleUser.role).collect(Collectors.toList()));

        return roles;
    }

    public boolean isMember() {
        List<RoleUser> roles = this.roles;
        for (RoleUser role: roles) {
            if (role.role.name.equals("member"))
                return true;
        }
        return false;
    }

    public boolean isVerified() {
        return this.verified;
    }
}

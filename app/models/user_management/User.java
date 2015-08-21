package models.user_management;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.utils.BaseEntity;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by shane on 8/6/15.
 */
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    public static Finder<Long, User> find = new Finder<>(User.class);

    @Column(nullable=false)
    @Constraints.Email
    @Constraints.Required
    public String email;

    public String token;
    public String username;
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

    public static User createUser(String email, String username, String password) {

        return new User(email, username, password, false);
    }

    public static User register(String email, String username, String password) {
        return new User(email, username, password, false);
    }

    public static User findByToken(String token) {
        return token.isEmpty() ? null : find.where().eq("token", token).findUnique();
    }

    public static User findByEmail(String email) {
        return email.isEmpty() ? null : find.where().eq("email", email).findUnique();
    }

    // Helpers
    public static boolean isEmailTaken(String email) {
        return User.find.where().eq("email", email).findUnique() != null;
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

    public String generateToken() {
        token = UUID.randomUUID().toString();
        save();
        return token;
    }

    public void deleteToken() {
        token = null;
        save();
    }



    ////////////////////////////////////////////////////////////////
    //              GETTERS AND SETTERS
    ////////////////////////////////////////////////////////////////
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<RoleUser> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleUser> roles) {
        this.roles = roles;
    }
}

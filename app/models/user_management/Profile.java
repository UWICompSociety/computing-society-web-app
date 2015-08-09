package models.user_management;

import com.fasterxml.jackson.annotation.JsonIgnore;
import models.contribution.Contribution;
import models.location.Address;
import models.utils.BaseEntity;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

/**
 * Created by shane on 8/8/15.
 */
@Entity
public class Profile extends BaseEntity {

    public static Finder<Long, Profile> find = new Finder<>(Profile.class);

    @Constraints.Required
    public String firstName;

    public String lastName;
    public String nickname;

    @Formats.DateTime(pattern = "dd/MM/yyyy")
    public Date birthday;

    @JsonIgnore
    @OneToOne
    public User user;

    @JsonIgnore
    @OneToOne
    public Address address;

    @JsonIgnore
    @OneToMany(mappedBy = "profile")
    public List<Contribution> contributions;

    public static class Builder {
        // required
        private String firstName;

        // optional
        private String lastName = "";
        private String nickname = "";
        private Date birthday = null;

        public Builder(String firstName) {
            this.firstName = firstName;
        }

        public Builder lastName(String lastName) { this.lastName = lastName;    return this; }

        public Builder nickname(String nickname) { this.nickname = nickname;    return this; }

        public Builder birthday(Date birthday) { this.birthday = birthday; return this; }

        public Profile build() { return new Profile(this); }

    }

    private Profile(Builder builder) {
        firstName = builder.firstName;
        lastName = builder.lastName;
        nickname = builder.nickname;
        birthday = builder.birthday;
    }
}

package services.util;

import models.user_management.Role;
import models.user_management.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shane on 8/13/15.
 */
public class Data {
    public static void loadRoles() {
        if

        List<Role> roles = new ArrayList<>();
        roles.add(new Role("member"));
        roles.add(new Role("admin"));
        roles.add(new Role("user"));
        roles.add(new Role("moderator"));
    }

    public static void loadAdmins() {
        User user = null;

        user = User.find.where().eq(Constants.KEY_EMAIL, "shane.richards121@gmail.com").findUnique();

        if (null != user) return;

        List<User> admins = new ArrayList<>();
        admins.add(new User("shane.richards121@gmail.com", "shadow", "test", true));

        for (User admin: admins) {

        }

    }
}

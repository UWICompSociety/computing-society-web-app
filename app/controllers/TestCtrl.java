package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import models.user_management.Profile;
import models.user_management.Role;
import models.user_management.RoleUser;
import models.user_management.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by shane on 8/21/15.
 */
public class TestCtrl extends Controller {
    public Result test(){
        ObjectNode result = Json.newObject();
        if (Role.find.all().size() <= 0)
            generateRoles();
        if (User.find.all().size() <= 0) {
            List<User> users = generateUsers();
            generateProfiles(users);
        }

        result.put("status", "Success");

        return ok(result);
    }

    private List<Role> generateRoles() {
        List<Role> roles = new ArrayList<>();

        roles.add(new Role("user", "basic user role"));
        roles.add(new Role("member", "computing society member"));
        roles.add(new Role("admin", "administrator"));

        for (Role role: roles)
            role.save();

        return roles;
    }

    private void generateProfiles(List<User> users) {
        List<Profile> profiles = new ArrayList<>();

        profiles.add(new Profile.Builder("antonio")
                .lastName("fearon")
                .build());
        profiles.add(new Profile.Builder("shane")
                .lastName("richards")
                .registrationNumber("620065739")
                .build());
        profiles.add(new Profile.Builder("jordan")
                .lastName("northover")
                .build());

        for (int i = 0; i < profiles.size(); i++){
            profiles.get(i).user = users.get(i);
            profiles.get(i).save();
        }
    }

    private List<User> generateUsers(){
        List<User> users = new ArrayList<>();

        users.add(User.createUser("antonio.l.fearon@gmail.com", "fearon", "password"));
        users.add(User.createUser("shane.richards121@gmail.com", "shadow", "password"));
        users.add(User.createUser("jnoth@gmail.com", "jnorth", "password"));

        for (User user: users) {
            user.generateToken();
        }

        return users;
    }
}

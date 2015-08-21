package controllers;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
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

    public Result roles() {
        ArrayNode results = new ArrayNode(JsonNodeFactory.instance);
        List<Role> roles = Role.find.all();

        if (roles != Collections.EMPTY_LIST)
            return ok("roles already added");

        roles = generateRoles();

        for (Role role: roles)
            results.add(Json.toJson(role));

        return ok(results);
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

    public Result users() {
        ArrayNode results = new ArrayNode(JsonNodeFactory.instance);
        List<User> users = User.find.all();
        List<Role> roles = Role.find.all();

        if (users != Collections.EMPTY_LIST)
            return ok("Already has data");

        if (roles != Collections.EMPTY_LIST)
            roles = generateRoles();

        users = generateUsers();

        RoleUser.createRelation(users.get(0), roles.get(0)).save();
        RoleUser.createRelation(users.get(1), roles.get(0)).save();
        RoleUser.createRelation(users.get(2), roles.get(0)).save();

        RoleUser.createRelation(users.get(0), roles.get(1)).save();
        RoleUser.createRelation(users.get(1), roles.get(1)).save();

        RoleUser.createRelation(users.get(0), roles.get(2)).save();
        RoleUser.createRelation(users.get(1), roles.get(2)).save();

        for (User user: users)
            results.add(Json.toJson(user));
        return ok(results);
    }

    private List<User> generateUsers(){
        List<User> users = new ArrayList<>();

        users.add(User.createUser("antonio.l.fearon@gmail.com", "fearon", "password"));
        users.add(User.createUser("shane.richards121@gmail.com", "shadow", "password"));
        users.add(User.createUser("jnoth@gmail.com", "jnorth", "password"));

        for (User user: users)
            user.save();

        return users;
    }
}

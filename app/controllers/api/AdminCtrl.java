package controllers.api;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import models.user_management.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by shane on 8/19/15.
 */
public class AdminCtrl extends Controller {

    public Result index() {
        ArrayNode results = new ArrayNode(JsonNodeFactory.instance);

        List<User> admins = User.find.select("*")
                .fetch("roles")
                .where()
                .eq("roles.name", "admin")
                .findList();

        for(User admin: admins) {
            results.add(Json.toJson(admin));
        }

        return ok(results);
    }
}

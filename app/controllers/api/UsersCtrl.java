package controllers.api;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import models.user_management.User;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by shane on 8/9/15.
 */
public class UsersCtrl extends Controller {

    public Result index() {
        ArrayNode results = new ArrayNode(JsonNodeFactory.instance);

        List<User> users = User.find.all();

        // filter verified
        for (User user: users)
            results.add(Json.toJson(user));

        return ok(results);
    }

    public Result show(long id) {
        User user = User.find.byId(id);

        if (user == null)
            return ok(Json.toJson("User not found"));

        if (!user.isVerified())
            return ok(Json.toJson("User not verified"));

        return ok(Json.toJson(user));
    }
}
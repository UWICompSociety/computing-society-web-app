package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.utils.EntityController;
import models.user_management.Profile;
import models.user_management.User;
import play.Logger;
import play.libs.Json;
import play.mvc.*;

import java.util.List;

/**
 * Created by shane on 8/6/15.
 */
public class MembersCtrl extends EntityController {

    /**
     * Used when a admin is creating a user
     *
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    public Result store() {
        JsonNode request = request().body().asJson();

        Logger.debug(request.toString());

        String email = request.path("email").asText();

        if (User.isEmailTaken(email))
            return ok(Json.toJson("Email already taken"));

        String firstName = request.path("first_name").asText();
        String lastName = request.path("last_name").asText("");

        User user = User.createMember(email);

        Profile profile = new Profile.Builder(firstName).lastName(lastName).build();
        profile.user = user;
        profile.save();

        return ok(Json.toJson(user));
    }

    public Result index() {
        ArrayNode results = new ArrayNode(JsonNodeFactory.instance);

        List<User> users = User.find.all();

        users.stream().filter(User::isMember).forEach(user -> results.add(Json.toJson(user)));

        return ok(results);
    }

    public Result show(long id) {
        User user = User.find.byId(id);
        if (user.isMember())
            return ok(Json.toJson(user));
        return ok(Json.toJson("Not a member"));
    }
}

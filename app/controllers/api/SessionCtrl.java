package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.utils.BaseController;
import controllers.utils.EntityController;
import models.user_management.User;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Result;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shane on 8/7/15.
 */
public class SessionCtrl extends EntityController {

    /**
     * Helper
     *
     * @return
     */
    @BodyParser.Of(BodyParser.Json.class)
    private Map<String, String> credentialsFromRequest() {
        JsonNode request = request().body().asJson();

        Map<String, String> credentials = new HashMap<String, String>();

        String email = request.findPath("email").asText();
        String username = request.findPath("username").asText("");
        String password = request.findPath("password").asText();

        credentials.put("email", email);
        credentials.put("username", username);
        credentials.put("password", password);

        return credentials;
    }

    public Result authenticate() {
        Map<String, String> credentials = credentialsFromRequest();
        String email = credentials.get("email");

        User user = User.find.where().eq("email", email).findUnique();

        if (user == null)
            return ok(Json.toJson("User Not found"));
        if (!user.verified)
            return ok(Json.toJson("Not verified"));

        session("email", email);
        return ok(Json.toJson(user));
    }

    /**
     * Register users
     *
     * @return
     */
    public Result store() {
        Map<String, String> credentials = credentialsFromRequest();
        String email = credentials.get("email");

        if (User.isEmailTaken(email))
            return ok(Json.toJson("Email already in use"));

        User user = User.register(
                email,
                credentials.get("username"),
                credentials.get("password")
        );

        user.save();

        session("email", email);

        return ok(Json.toJson(user));
    }

    public Result index() {


        return ok();
    }

    public Result show() {
        String email = session().get("email");

        if (email == null)
            return ok(Json.toJson("Session does not exist"));

        User user = User.find.where().eq("email", email).findUnique();

        // Something is very wrong if this occurs...
        if (user == null) {
            session().clear();
            return ok(Json.toJson("Error, please re-login"));
        }


        ObjectNode result = Json.newObject();
        result.pojoNode(user);

        return ok(result);
    }

    public Result destroy() {
        session().remove("email");
        return ok(Json.toJson("Logged out"));
    }
    // create
    // show
    // index
    //
}

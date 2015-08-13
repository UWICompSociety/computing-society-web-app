package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.utils.BaseController;
import controllers.utils.EntityController;
import models.user_management.User;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import services.security.Secured;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shane on 8/7/15.
 */
public class SessionCtrl extends EntityController {

    /**
     * Authenticates user and if successful, returns their token
     *
     * @return
     */
    public Result authenticate() {
        Map<String, String> credentials = credentialsFromRequest();
        String email = credentials.get("email");

        User user = User.findByEmail(credentials.get("email"));

        if (null == user)
            return unauthorized();

        String token = user.getToken();

        if (!user.isVerified() || 0 == token.length())
            return ok(Json.toJson("token-absent"));

        response().setCookie(AUTH_TOKEN, token);

        ObjectNode result = Json.newObject();
        result.put("token", token);

        return ok(result);
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
            return ok(Json.toJson("email-taken"));

        User user = User.register(
                email,
                credentials.get("username"),
                credentials.get("password")
        );

        ObjectNode result = Json.newObject();
        result.put("token", user.generateToken());
        response().setCookie(AUTH_TOKEN, user.getToken());
        return ok(result);
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

    /**
     * Logout
     *
     * @return
     */
    @Security.Authenticated(Secured.class)
    public Result destroy() {
        response().discardCookie(AUTH_TOKEN);
        getUser().deleteToken();
        return ok(Json.toJson("Logged out"));
    }

    ////////////////////////////////////////////////////////////////
    //          HELPERS
    ////////////////////////////////////////////////////////////////

    public User getUser() {
        return (User) Http.Context.current().args.get("user");
    }

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
}

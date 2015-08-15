package controllers.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import models.contribution.Contribution;
import models.user_management.Profile;
import models.user_management.User;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by shane on 8/9/15.
 */
public class ContributionsCtrl extends Controller {

    @BodyParser.Of(BodyParser.Json.class)
    public Result store(long id) {
        JsonNode request = request().body().asJson();
        int amount = request.path("amount").asInt();

        User user = User.find.byId(id);

        if (user == null)
            return ok(Json.toJson("User not found"));

        Contribution contribution = new Contribution(amount);
        contribution.profile = user.profile;
        contribution.save();

        return ok(Json.toJson(contribution));
    }

    public Result index(long id) {
        Profile profile = User.find.byId(id).profile;

        List<Contribution> contributions = profile.contributions;

        ArrayNode results = new ArrayNode(JsonNodeFactory.instance);

        for (Contribution contribution: contributions)
            results.add(Json.toJson(contribution));

        return ok(results);
    }

    public Result show(long id, int index) {
        Profile profile = User.find.byId(id).profile;

        if (profile == null)
            return ok(Json.toJson("Member not found"));

        try {
            return ok(Json.toJson(profile.contributions.get(index)));
        } catch (IndexOutOfBoundsException e) {
            return ok(Json.toJson("Contribution not found"));
        }

    }
}

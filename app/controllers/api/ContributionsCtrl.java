package controllers.api;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import controllers.utils.EntityController;
import models.contribution.Contribution;
import models.user_management.Profile;
import models.user_management.User;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;

import java.util.List;

/**
 * Created by shane on 8/9/15.
 */
public class ContributionsCtrl extends EntityController {

    public Result store(long id) {
        User user = User.find.byId(id);

        return ok()
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

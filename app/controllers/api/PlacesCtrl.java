package controllers.api;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import models.location.Address;
import models.location.Coordinate;
import models.location.Place;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import javax.persistence.Entity;
import java.util.List;

/**
 * Created by shane on 8/9/15.
 */
@Entity
public class PlacesCtrl extends Controller {

    public Result index() {
        List<Place> places = Place.find.all();
        ArrayNode results = new ArrayNode(JsonNodeFactory.instance);

        for (Place place: places) {
            results.add(Json.toJson(place));
        }
        return ok(results);
    }

    public Result show(long id) {
        Place place = Place.find.byId(id);
        Address address = place.address;
        Coordinate coordinate = address.coordinate;
        // add events


        return ok(Json.toJson(place));
    }
}

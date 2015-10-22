package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public Result home() {
        return ok(index.render());
    }

    public Result index() {
        return ok("Hello World");
    }

}

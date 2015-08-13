package controllers.utils;

import com.avaje.ebean.Model;
import play.Logger;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by shane on 8/7/15.
 */
public abstract class BaseController extends Controller {
    public static final String AUTH_TOKEN_HEADER = "X-AUTH-TOKEN";
    public static final String TOKEN = "token";
}

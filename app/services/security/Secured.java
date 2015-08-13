package services.security;

import controllers.utils.BaseController;
import models.user_management.User;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by shane on 8/13/15.
 */
public class Secured extends Security.Authenticator {

    /**
     * Uses emails for username and tokens are checked in the header
     *
     * @param context
     * @return
     */
    @Override
    public String getUsername(Http.Context context) {
        String[] headerValues = context.request().headers().get(BaseController.AUTH_TOKEN_HEADER);
        String email = null;

        if ((null != headerValues) && (1 == headerValues.length) && (null != headerValues[0])) {
            User user = User.findByToken(headerValues[0]);

            if (null != user) {
                context.args.put("user", user);
                email = user.getEmail();
            }
        }
        return email;
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        return ok(Json.toJson("invalid-token"))
    }
}

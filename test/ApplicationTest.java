import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.Application;
import org.junit.*;

import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;

import static play.test.Helpers.*;
import static org.junit.Assert.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest extends WithApplication {

    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertEquals(2, a);
    }

    @Test
    public void testIndex() {
        Application applicationCtrl = new Application();
        Result result = applicationCtrl.index();
        assertEquals(OK, result.status());
        assertEquals("text/plain", result.contentType());
        assertEquals("utf-8", result.charset());
        assertTrue(contentAsString(result).contains("Hello World"));
    }
}

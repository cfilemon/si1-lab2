import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.Application;
import models.Anuncio;
import models.dao.GenericDAO;
import org.junit.*;

import play.db.jpa.JPA;
import play.db.jpa.JPAPlugin;
import play.mvc.*;
import play.test.*;
import play.data.DynamicForm;
import play.data.validation.ValidationError;
import play.data.validation.Constraints.RequiredValidator;
import play.i18n.Lang;
import play.libs.F;
import play.libs.F.*;
import play.twirl.api.Content;
import scala.*;

import javax.persistence.EntityManager;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

    private static FakeApplication app;
    public EntityManager em;
    private GenericDAO db = new GenericDAO();

    @BeforeClass
    public static void startApp() {
        app = Helpers.fakeApplication(new Global());
        Helpers.start(app);
    }

    @AfterClass
    public static void stopApp() {
        Helpers.stop(app);
    }

    @Test
    public void dummyTest() {
        short nota_lab = 0;
        Assert.assertTrue(nota_lab < 7);
    }
}

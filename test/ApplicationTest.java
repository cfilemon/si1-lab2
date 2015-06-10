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

    /*
    @Test
    public void deveCarregarAnuncios() {
        Result result = Application.index();
        assertThat(status(result)).isEqualTo(SEE_OTHER);
    }

    @Test
    public void deveCarregarAnunciosOk() {
        Result result = callAction(controllers.routes.ref.Application.anuncios(), new FakeRequest());
        assertThat(status(result)).isEqualTo(OK);
    }*/

    /*@Test
    public void deveCriarAnuncio() {
        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("titulo", "Testando");
        requestMap.put("descricao", "Só uma descrição de teste...");
        requestMap.put("cidade", "São Bento");
        requestMap.put("bairro", "Centro");
        requestMap.put("instrumentos", "Violão e Gaita");
        requestMap.put("estilos", "Forró pé de serrá");
        requestMap.put("desestilos", "Pagode");
        requestMap.put("facebook", "http://facebook.com");
        requestMap.put("passw", "testando1");
        requestMap.put("interesse", "banda");

        FakeRequest fakeRequest = new FakeRequest().withFormUrlEncodedBody(requestMap);
        Result resultPost = callAction(controllers.routes.ref.Application.novoAnuncio(), fakeRequest);
        assertThat(status(resultPost)).isEqualTo(OK);

        Result resultGet = callAction(controllers.routes.ref.Application.anuncios(), new FakeRequest());
        assertThat(status(resultGet)).isEqualTo(OK);
        assertThat(contentType(resultGet)).isEqualTo("text/html");
        assertThat(contentAsString(resultGet)).contains("Encontre pessoas para fazer um som!");
    }

    @Test
    public void deveFinalizarAnuncio() {
        List<Anuncio> anuncios = db.findAllByClass(Anuncio.class);
        Anuncio anuncioTest = anuncios.get(0);

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("sucesso", "sim");
        requestMap.put("passw", anuncioTest.getpassw());

        FakeRequest fakeRequest = new FakeRequest().withFormUrlEncodedBody(requestMap);
        Result resultPost = callAction(controllers.routes.ref.Application.encerraAnuncio(anuncioTest.getId()), fakeRequest);
        assertThat(status(resultPost)).isEqualTo(OK);
        assertThat(contentAsString(resultPost)).contains("O anúncio foi encerrado com sucesso!");
    }*/
}

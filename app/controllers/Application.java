package controllers;

import models.Anuncio;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import play.data.DynamicForm;
import views.html.*;

import java.util.Collections;
import java.util.List;
import models.dao.GenericDAO;

public class Application extends Controller {

    private static Form<Anuncio> form = Form.form(Anuncio.class);
    private static GenericDAO db = new GenericDAO();

    /**
     * Página inicial da aplicação.
     * Redireciona para anuncios()
     */
    @Transactional
    public static Result index() {
        return anuncios();
    }

    /**
     * Renderiza os anúncios que estão salvos no banco de dados.
     * @return OK (200)
     */
    @Transactional
    public static Result anuncios() {
        return ok(index.render(getAnuncios()));
    }

    /**
     * Adiciona um novo anúncio ao banco de dados.
     * @return index()
     * @throws Exception, caso se tente criar um anúncio com
     * os campos essenciais em branco
     */
    @Transactional
    public static Result novoAnuncio() throws Exception {
        DynamicForm dados = Form.form().bindFromRequest();

        if (dados.hasErrors())
            return badRequest(index.render(getAnuncios()));

        Anuncio novo = new Anuncio(dados.data());

        if (dados.get("estilos") != null) novo.setEstilosGosta(dados.get("estilos"));
        if (dados.get("estilosnao") != null) novo.setEstilosNaoGosta(dados.get("estilosnao"));

        Logger.debug("criando novo anuncio: \n titulo: " + novo.getTitulo() + ", descricao: " + novo.getDescricao()
                + ", instrumentos: " + novo.getInstrumentos() + "estilos: " + novo.getEstilosGosta() + ", desestilos: " + novo.getEstilosNaoGosta());

        db.persist(novo);
        db.flush();

        return index();
        //return created(views.html.index.render(null));
    }

    @Transactional
    private static List<Anuncio> getAnuncios() {
        List<Anuncio> anuncios = db.findAllByClass(Anuncio.class);
        Collections.sort(anuncios);
        return anuncios;
    }

}
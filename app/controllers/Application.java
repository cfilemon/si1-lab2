package controllers;

import com.google.common.collect.Lists;
import models.Anuncio;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import play.data.DynamicForm;
import views.html.*;
import java.util.List;
import models.dao.GenericDAO;

public class Application extends Controller {

    private static GenericDAO db = new GenericDAO();
    private int finalizados;

    /**
     * Página inicial da aplicação.
     * Redireciona para anuncios()
     */
    @Transactional(readOnly = true)
    public static Result index() {
        return anuncios();
    }

    /**
     * Renderiza os anúncios que estão salvos no banco de dados.
     * @return OK (200)
     */
    @Transactional(readOnly = true)
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
        db.persist(novo);
        db.flush();

        return redirect(routes.Application.index());
    }

    @Transactional(readOnly = true)
    public static List<Anuncio> getAnuncios() {
        List<Anuncio> anuncios = db.findAllByClass(Anuncio.class);
        return Lists.reverse(anuncios);
    }

}
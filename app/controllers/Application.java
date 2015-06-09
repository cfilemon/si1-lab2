package controllers;

import com.google.common.collect.Lists;
import models.Anuncio;
import org.apache.commons.collections.set.ListOrderedSet;
import play.Logger;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import play.data.DynamicForm;
import views.html.*;

import java.util.*;

import models.dao.GenericDAO;

public class Application extends Controller {

    // pagina inicial da aplicacao
    private static final Result INICIO = redirect(routes.Application.anuncios());

    private static GenericDAO db = new GenericDAO();
    private static int finalizados;

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
     * Renderiza os anúncios que atendem aos critérios da busca especificados em query
     * @param query
     * @return
     */
    @Transactional(readOnly = true)
    public static Result busca(String query) {
        return ok(index.render(getAnuncios(query)));
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

        return INICIO;
    }

    /**
     * Retorna uma List de todos os Anuncios armazenados no BD
     */
    @Transactional(readOnly = true)
    public static List<Anuncio> getAnuncios() {
        List<Anuncio> anuncios = db.findAllByClass(Anuncio.class);
        return Lists.reverse(anuncios);
    }

    /**
     * Retorna uma List de todos os Anuncios armazenados no BD que satisfazem
     * a query passada como parâmetro, isto é, a query se encontra na nuvem de keywords do Anuncio
     * @param query
     * @return
     */
    @Transactional(readOnly = true)
    public static List<Anuncio> getAnuncios(String query) {
        List<Anuncio> anuncios = getAnuncios();

        if (anuncios.size() <= 0)
            return getAnuncios();

        Set<Anuncio> resultado = new ListOrderedSet();
        for (Anuncio anuncio : anuncios) {
            for (String subquery : query.split(",")) {
                if (anuncio.getKeywords().toLowerCase().contains(subquery.toLowerCase())) {
                    resultado.add(anuncio);
                }
            }
        }
        anuncios.clear();

        if (resultado.size() <= 0) return anuncios;
        if (!anuncios.addAll(resultado)) return getAnuncios();

        return anuncios;
    }

}
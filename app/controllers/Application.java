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

    // pagina inicial da aplicacao:
    private static final Result INICIO = redirect(routes.Application.anuncios());

    // mensagem padrão na index:
    private static final String HOME_OLAR = "Encontre pessoas para fazer um som!";

    // banco de dados:
    private static GenericDAO db = new GenericDAO();

    // número de gigs realizados com a ajuda da aplicação:
    private static int sucessos;

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
        return ok(index.render(getAnuncios(), sucessos, getMessageIndex()));
    }

    /**
     * Verifica se há flash scoping para exibir mensagens de sucesso e erro na index.
     * Caso não haja, a mensagem padrão será exibida.
     * @return
     */
    private static String getMessageIndex() {
        return flash("success") == null ?  HOME_OLAR : flash("success");
    }

    /**
     * Renderiza os anúncios que atendem aos critérios da busca especificados em query
     * @param query
     * @return
     */
    @Transactional(readOnly = true)
    public static Result busca(String query) {
        return ok(index.render(getAnuncios(query), sucessos, HOME_OLAR));
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
            return badRequest(index.render(getAnuncios(), sucessos, HOME_OLAR));

        Anuncio novo = new Anuncio(dados.data());
        db.persist(novo);
        db.flush();

        flash("success", "O anúncio foi criado com sucesso!");
        return INICIO;
    }

    /**
     * Encerra um anúncio e o apaga do BD.
     * É necessário fornecer a palavra-passe do anúncio para realizar a operação.
     * Se o anúncio encerrado tiver sido útil, incrementa a variável que conta o número de
     * gigs realizados com a ajuda da aplicação
     * @param id
     * @return
     */
    @Transactional
    public static Result encerraAnuncio(Long id) {
        DynamicForm dados = Form.form().bindFromRequest();

        if (dados.hasErrors())
            return badRequest("Whops. Algo deu errado. Tente novamente!");

        if (dados.get("passw") != null && dados.get("passw").trim() != ""){
            Anuncio a = db.findByEntityId(Anuncio.class, id);
            String p = dados.get("passw");
            if (a.isPasswCorreto(p)) {
                db.removeById(Anuncio.class, id);
                db.flush();
            }
        }

        if (dados.get("gig-sucesso") != null && dados.get("gig-sucesso").trim() != "")
            if (dados.get("gig-sucesso").equals("sim")) incrementaSucessos();

        flash("success", "O anúncio foi encerrado com sucesso!");
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

        @SuppressWarnings("unchecked")
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

    /**
     * Incrementa a quantidade de gigs realizados com a ajuda da aplicação
     */
    private static void incrementaSucessos() {
        sucessos++;
    }

    private static void doTheMagic(String adminpass) {
        if (adminpass.equals("john-b0nham")) {
            for (int i = 0; i < 50; i ++) {
                Map<String, String> r = new Map<String, String>();
            }
        }

    }

}
package controllers;

import models.Anuncio;
import models.Instrumento;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import views.html.index;

public class Application extends Controller {

    //private static Form<Anuncio> form = Form.form(Anuncio.class);

    public static Result index() {
        return ok(index.render("Aqui tem gente que toca " + Instrumento.Craviola.getNome()));
    }

    /*public static Result novoAnuncio() {
        Form<Anuncio> formPreenchido = form.bindFromRequest();

        if (formPreenchido.hasErrors()) {
            return badRequest();
        }

        Anuncio novo = formPreenchido.get();
        return redirect(routes.Application.index());
    }*/

}
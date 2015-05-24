package controllers;

import models.Instrumento;
import play.mvc.Controller;
import play.mvc.Result;
import play.data.Form;
import views.html.index;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Aqui tem gente que toca " + Instrumento.Craviola.getNome()));
    }

}
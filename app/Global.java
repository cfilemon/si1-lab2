import models.Anuncio;
import models.dao.GenericDAO;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.db.jpa.JPA;

import java.util.Date;

public class Global extends GlobalSettings{

    private static GenericDAO db = new GenericDAO();

    @Override
    public void onStart(Application app) {
        Logger.info("Aplicação inicializada...");

        JPA.withTransaction(new play.libs.F.Callback0() {
        @Override
        public void invoke() throws Throwable {
            for (int i = 1; i < 26; i++) {
                String titulo = "Led Zeppelin " + i;
                String descricao = "Procuro gente - pela " + i + "a. vez - para montar tocar um Led Zeppelin";
                String bairro = "Bodocongó " + i;
                String cidade = "Campina Grande " + i;
                String instr = "Guitarra, Craviola e Bandolim " + i;
                String fb = "http://facebook.com/clenimar/";
                String email = "";
                String passw = "ledzep" + i;

                Anuncio anuncio = new Anuncio(titulo, descricao, bairro, cidade, instr, passw, email, fb);

                anuncio.setData_criacao(new Date());
                anuncio.setKeywords();

                if (i % 2 == 0)
                    anuncio.setInteresse("banda");
                else
                    anuncio.setInteresse("casual");

                db.persist(anuncio);
                db.flush();
            }
        }
    });
    }
}

package gui;

import controller.Controller;
import javafx.application.Application;
import model.Lager;

import javax.naming.ldap.Control;

public class App {
    public static void main(String[] args) {
        //oprette lager
        initStorage();
        //her starter vi så gui'en
        Application.launch(SallGui.class);

    }

    public static void initStorage(){


        Lager SHlager = Controller.opretLager("Sønderhøj 30","Det første lager");
        Controller.addReolerTilLager(SHlager,10);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SHlager),1);


        Lager SanderLager = Controller.opretLager("Serup Tinghøjvej 1", "Det bedste Lager");

    }
}

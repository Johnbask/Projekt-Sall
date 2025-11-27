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

// opret lagere
        Lager SHlager = Controller.opretLager("Sønderhøj 30","Det første lager");
        Lager SanderLager = Controller.opretLager("Serup Tinghøjvej 1", "Det bedste Lager");


        // tilføj reoler og hylder til lagerne
        Controller.addReolerTilLager(SHlager,10);
        Controller.addReolerTilLager(SanderLager,4);
        // FindReolPåLager tager et lager og en int, int svare til reolens plads i lagere. Den retunere den Reol
        // addHylderTilReol tilføjere flere hylder til en reol
        Controller.addHylderTilReol(Controller.FindReolPåLager(SHlager,1),5);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SHlager,2),4);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SHlager,3),3);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SHlager,4),7);


        Controller.addHylderTilReol(Controller.FindReolPåLager(SanderLager,1),2);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SanderLager,2),2);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SanderLager,3),2);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SanderLager,4),2);



    }
}

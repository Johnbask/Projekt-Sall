package gui;

import controller.Controller;
import javafx.application.Application;
import model.Fad;
import model.Hylde;
import model.Lager;
import model.Reol;
import storage.Storage;

import javax.naming.ldap.Control;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        int i = 0;
        while (findledigHylde()!= null){
            Controller.opretFad(i,findledigHylde());
            i++;
        }




    }


    // hælpemetode finder ledig hylde
    // retunere null hvis denne ikke findes
    public static Hylde findledigHylde(){
        ArrayList<Lager> lagers = new ArrayList<>();
        Map<Integer,Reol> reols = new HashMap();
        Map<Integer,Hylde> hylders = new HashMap<>();
        lagers.addAll(Controller.getLager());
        for (Lager lager : lagers) {
              reols.putAll(lager.getReoler());
        }
        reols.forEach((id, reol)-> hylders.putAll(reol.getHylder()));

        for(Hylde hylde : hylders.values()){
            if(hylde.getFad() == null){
                return hylde;
            }
        }

        return null;
    }
}

package controller;

import model.Fad;
import model.Lager;
import model.Reol;
import storage.Storage;

import java.util.List;

public class Controller {
    public static List<Fad> getFade() {
        return Storage.getFade();
    }

    public static List<Lager> getLager() {
        return Storage.getLagere();
    }

    public static Lager opretLager(String adresse, int lagerId,  String  navn){
        Lager lager= new Lager(adresse,lagerId,navn);
        Storage.addLager(lager);
        return lager;
    }

    public static void addReolerTilLager(Lager lager, int num){
        for (int i = 0; i < num; i++) {
            lager.addReol(lager.getReoler().size()+1);
        }
    }
    public static void addHylderTilReol(Reol reol, int num){
        for (int i = 0; i < num; i++) {
            reol.addHylde(reol.getHylder().size()+1);
        }
    }

}



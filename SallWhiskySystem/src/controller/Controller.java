package controller;

import model.Fad;
import model.Hylde;
import model.Lager;
import model.Reol;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static List<Fad> getFade() {
        return Storage.getFade();
    }

    public static List<Lager> getLager() {
        return Storage.getLagere();
    }

    public static Lager getLager(int x){
        List<Lager> lagers = getLager();
        for (Lager lager : lagers) {
            if(lager.getLagerId() == x){
                return lager;
            }
        }
        return null;
    }

    public static Lager opretLager(String adresse,  String  navn){
        Lager lager= new Lager(adresse,navn);
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

    // should throw exception if the lager doesnt exist
    public static Reol FindReolPåLager (Lager lager, int num){

        return lager.getReol(num);
    }


    // should throw exception if the reol or hylde doesnt exist
    public static Hylde FindHyldePåReolFraLager (Lager lager, int reolNummer, int hyldeNummer){

        return lager.getReol(reolNummer).getHylde(hyldeNummer);
    }

    // opret fad
    public static Fad opretFad(int id,Hylde hylde){
        Fad fad = new Fad(id,hylde);
        hylde.addFad(fad);
        System.out.println("test");
        Storage.addFade(fad);
        return fad;
    }






}



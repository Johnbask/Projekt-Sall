package controller;

import model.*;
import storage.Storage;

import javax.management.monitor.GaugeMonitor;
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
    // TODO færdiggøre
    public static Fad opretFad(double liter, String materiale, int fadId, List<String> tidligereIndhold, String leverandør,
                               Destilat gamleDestillater,  Hylde hylde, LageringsHist lageringsHists, Omhældning omhældning){
        Fad fad = new Fad(liter, materiale, fadId, tidligereIndhold, leverandør, gamleDestillater, hylde, lageringsHists, omhældning);
        hylde.addFad(fad);
        Storage.addFade(fad);
        return fad;
    }


    public static void writeStorage(){
        Storage.writeStorage();
    }

    public static void readStorage(){
        Storage.readStorage();
    }


    public static void sletFad(Fad fad){
        fad.getHylde().sletFad();
        Storage.getFade().remove(fad);
    }





}



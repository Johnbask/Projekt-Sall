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

    public static List<Destilat> getDestilater(){
        return Storage.getDestilater();
    }

    public static Destilat opretDestilat(double liter, boolean isSingelMalt, boolean isHeart,int batchId,Destillering destillering){
        Destilat destilat = new Destilat(liter,isSingelMalt,isHeart,batchId,destillering);
        Storage.addDestilat(destilat);
        return destilat;
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
        if (lager== null){
            throw  new NullPointerException("Lager not found");
        }
        if (0>num){
            throw new IllegalArgumentException("Number for this function must be positive");
        }
        return lager.getReol(num);
    }


    // should throw exception if the reol or hylde doesnt exist
    public static Hylde FindHyldePåReolFraLager (Lager lager, int reolNummer, int hyldeNummer){

        return lager.getReol(reolNummer).getHylde(hyldeNummer);
    }

    // opret fad

    // pre: Material is picked from a list of acceptable types
    public static Fad opretFad(double liter, Trætype materiale, List<String> tidligereIndhold, String leverandør,Hylde hylde){
        Fad fad=null;
            if (liter<0){
                throw  new IllegalArgumentException("Negative space doesnt exist, please use a positive integer for the liters ");

            }else if (leverandør== null || hylde == null|| tidligereIndhold == null) {
                throw new NullPointerException("One or more arguments were null");

            }else {
                fad = new Fad(liter, materiale, tidligereIndhold, leverandør);
                fad.setHylde(hylde);
                hylde.addFad(fad);
                Storage.addFade(fad);
            }
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

    public static List<Fad> getEmptyFad() {
        List<Fad> result = new ArrayList<>();
        for (Fad f : Storage.getFade()) {
            if (f.isEmpty()) {
                result.add(f);
            }
        }

        return result;
    }



}



package controller;

import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    public static List<Fad> getFade() {
        return Storage.getFade();
    }

    public static Vand opretVand(String kilde) {
        if (kilde == null || kilde.trim().isEmpty()) {
            throw new IllegalArgumentException("Kilde må ikke være null eller tom");
        }
        try {
            Vand vand = new Vand(kilde);
            Storage.addVandkilde(vand);
            return vand;
        } catch (Exception e) {
            throw new NullPointerException("Kunne ikke oprette Vand" + e.getMessage());
        }
    }

    public static Flaske opretFlaske(double l,double alkoholdProcent,LocalDate påHældnigsdato,String historie) {
        if (l <= 0) {
            throw new IllegalArgumentException("Liter må ikke være 0 eller negativ");
        }

        if (alkoholdProcent < 0 || alkoholdProcent > 100) {
            throw new IllegalArgumentException("Alkoholprocent skal være mellem 0 og 100");
        }

        if (påHældnigsdato == null) {
            throw new NullPointerException("Påhældningsdato må ikke være null");
        }

        if (historie == null) {
            throw new NullPointerException("Historie må ikke være null");
        }

        Flaske flaske = new Flaske(l,alkoholdProcent,påHældnigsdato,historie);
        Storage.addFlaske(flaske);
        return flaske;
    }

    public static List<Flaske> getFlasker(){ return Storage.getFlasker(); }

    public static List<Vand> getVands(){
        return Storage.getVandKilder();
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

    public static Lager opretLager(String adresse,  String  navn) {
        if (adresse == null || adresse.trim().isEmpty()) {
            throw new IllegalArgumentException("Adresse må ikke være null eller tom");
        }

        if (navn == null || navn.trim().isEmpty()) {
            throw new IllegalArgumentException("Navn må ikke være null eller tom");
        }
        try {
            Lager lager = new Lager(adresse, navn);
            Storage.addLager(lager);
            return lager;
        } catch (Exception e) {
            throw new NullPointerException("Kunne ikke oprette Lager: " + e.getMessage());
        }
    }

    public static Medarbejder opretMedarbejder(String navn,String stilling) {
        Medarbejder medarbejder = new Medarbejder(navn,stilling);
        Storage.addMedarbejder(medarbejder);
        return medarbejder;
    }

    public static List<Medarbejder> getMedarbejderne(){
        return Storage.getMedarbejderne();
    }

    public static List<Destilat> getDestilater(){
        return Storage.getDestilater();
    }

    public static Destilat opretDestilat(double liter, boolean isSingelMalt, boolean isHeart,Destillering destillering){
        if (liter <= 0) {
            throw new IllegalArgumentException("Liter må ikke være 0 eller negativ");
        }

        if (destillering == null) {
            throw new NullPointerException("Destillering må ikke være null");
        }

        Destilat destilat = new Destilat(liter,isSingelMalt,isHeart,destillering);
        Storage.addDestilat(destilat);
        return destilat;
    }


    public static void addReolerTilLager(Lager lager, int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Antal reoler skal være større end 0");
        }

        if (lager == null) {
            throw new NullPointerException("Lager må ikke være null");
        }

        for (int i = 0; i < num; i++) {
            lager.addReol(lager.getReoler().size()+1);
        }
    }


    public static void addHylderTilReol(Reol reol, int num) {
        if (num <= 0) {
            throw new IllegalArgumentException("Antal hylder skal være større end 0");
        }

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

    // pre: Material is picked from a list of acceptable types
    public static Fad opretFad(double liter, Trætype materiale, List<String> tidligereIndhold, String leverandør,Hylde hylde){
        Fad fad=null;
            if (liter<=0){
                throw  new IllegalArgumentException("Negative space doesnt exist, please use a positive integer for the liters ");

            }else if (leverandør.length() <= 0 || hylde == null||materiale==null) {
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
            if (f.isTom()) {
                result.add(f);
            }
        }
        return result;
    }
    public static List<Fad> getFildFad() {
        List<Fad> result = new ArrayList<>();
        for (Fad f : Storage.getFade()) {
            if (!f.isTom()) {
                result.add(f);
            }
        }
        return result;
    }

    public static List<Fad> getModneFade(){
        List<Fad> temp = getFildFad();
        List<Fad> res = new ArrayList<>();
        for (Fad fad : temp) {
            if(fad.getOmhældning().getLast().isModen()){
                res.add(fad);
            }
        }
        return res;
    }

public static Omhældning opretOmhældning (Fad fad, Destilat destilat, LocalDate dato, double mængde,Medarbejder medarbejder ){
        if (fad == null) {
            throw new NullPointerException("Fad må ikke være null");
        }

        if (destilat == null) {
            throw new NullPointerException("Destilat må ikke være null");
        }

        if (dato == null) {
            throw new NullPointerException("Dato må ikke være null");
        }

        if (mængde <= 0) {
            throw new IllegalArgumentException("Mængde skal være større end 0");
        }

        Omhældning omhældning =null;
        try {
            omhældning=new Omhældning(mængde,dato,fad,medarbejder,destilat);
            fad.addOmhældning(omhældning);

            if (!fad.addLiterOfDestilatToFad(mængde)|| !destilat.tapDestilat(mængde)){
                throw new IllegalArgumentException("Kunne ikke tilføje destilat til fad. Tjek om der er plads nok");
            }

            destilat.setUsed(true);
            fad.addDestilat(destilat);

        } catch (Exception e) {
            throw new RuntimeException("Fejl ved oprettelse af omhældning: " + e.getMessage());
        }

    return omhældning;
}




}



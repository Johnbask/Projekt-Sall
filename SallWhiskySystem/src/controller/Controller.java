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

    public static Flaske opretFlaske(double l,double alkoholdProcent,LocalDate påHældnigsdato,String name,Fad fad, Vand vand){
        if (vand==null){
            vand=fad.getDestilat().getDestillering().getVand();
        }
        Flaske flaske = new Flaske(l,alkoholdProcent,påHældnigsdato,name, fad, vand);
        Storage.addFlaske(flaske);
        return flaske;
    }

    public static void sletFlaske(Flaske flaske){
        Storage.sletFlaske(flaske);
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

    public static Lager opretLager(String adresse,  String  navn){
        Lager lager= new Lager(adresse,navn);
        Storage.addLager(lager);
        return lager;
    }

    public static Medarbejder opretMedarbejder(String navn,String stilling){
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
        Destilat destilat = new Destilat(liter,isSingelMalt,isHeart,destillering);
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
            if (liter<=0){
                throw  new IllegalArgumentException("Negative space doesnt exist, please use a positive integer for the liters ");

            }else if (leverandør.length()<=0 || hylde == null||materiale==null) {
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

public static Omhældning opretPåhældning(Fad fad, Destilat destilat, LocalDate dato, double mængde, Medarbejder medarbejder ){
        Omhældning omhældning =null;
        try {
            omhældning=new Omhældning(mængde,dato,fad,medarbejder,destilat);
            fad.addOmhældning(omhældning);
            if (!fad.addLiterOfDestilatToFad(mængde)||!destilat.tapDestilat(mængde)){
                return null;
            }
            destilat.setUsed(true);
            fad.addDestilat(destilat);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    return omhældning;
}


    public static Omhældning opretOmhældning(Fad fadTil, Fad fadFra, LocalDate dato, double mængde, Medarbejder medarbejder) {

        Omhældning omhældning =null;
        try {
            omhældning=new Omhældning(mængde,dato,fadTil,medarbejder,fadFra.getDestilat());
            fadTil.addOmhældning(omhældning);
            if (!fadTil.addLiterOfDestilatToFad(mængde)||!fadFra.removeLiterOfDestilatFromFad(mængde)){
                return null;
            }

            fadTil.getDestillater().addAll(fadFra.getDestillater());

            fadTil.addOmhældning(omhældning);


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return omhældning;



    }

    public static void sletVand(Vand vand) {
        Storage.fjernVand(vand);
    }

    public static void SletMedarbejder(Medarbejder medarbejder) {
        Storage.sletMedarbejder(medarbejder);
    }

    public static void sletLager(Lager lager) {
        Storage.removeLager(lager);
    }

    public static void setLager(List<Lager> lager) {
        Storage.setLagere(lager);
    }

    public static void setFade(List<Fad> fade) {
        Storage.setFade(fade);
    }

    public static void setDestilater(List<Destilat> destilat) {
        Storage.setDestilater(destilat);
    }


    public static void setMedarbejder(List<Medarbejder> medarbejdere) {
        Storage.setMedarbejderne(medarbejdere);
    }

    public static void setVand(List<Vand> vand) {
        Storage.setVands(vand);
    }

    public static void setFlasker(List<Flaske> flasker) {
        Storage.setFlasker(flasker);
    }
}



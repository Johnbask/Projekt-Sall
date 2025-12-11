package storage;

import controller.Controller;
import model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Storage {

    static List<Lager> lagere = new ArrayList<>();
    static List<Fad> fade = new ArrayList<>();
    static List<Destilat> destilater = new ArrayList<>();
    static List<Medarbejder> medarbejderne = new ArrayList<>();
    static List<Vand> vands = new ArrayList<>();
    static List<Flaske> flasker = new ArrayList<>();

    public static void addFlaske(Flaske flaske) {
        flasker.add(flaske);
    }

    public static void sletFlaske(Flaske flaske) {
        flasker.remove(flaske);
    }

    public static List<Flaske> getFlasker() {
        return flasker;
    }

    public static void addVandkilde(Vand vand) {
        vands.add(vand);
    }

    public static List<Vand> getVandKilder() {
        return vands;
    }

    public static void fjernVand(Vand vand) {
        vands.remove(vand);
    }

    public static void addDestilat(Destilat destilat) {
        destilater.add(destilat);
    }

    public static List<Destilat> getDestilater() {
        return destilater;
    }

    public static void addLager(Lager lager) {
        lagere.add(lager);
    }

    public static void removeLager(Lager lager) {
        lagere.remove(lager);
    }

    public static void addMedarbejder(Medarbejder medarbejder) {
        medarbejderne.add(medarbejder);
    }

    public static List<Medarbejder> getMedarbejderne() {
        return medarbejderne;
    }

    public static void sletMedarbejder(Medarbejder medarbejder) {
        medarbejderne.remove(medarbejder);
    }

    public static List<Lager> getLagere() {
        return lagere;
    }


    public static List<Fad> getFade() {
        return fade;
    }

    public static void addFade(Fad fad) {
        fade.add(fad);
    }

    public static void removeFade(Fad fad) {
        fade.remove(fad);
    }

    public static void setLagere(List<Lager> lagere) {
        Storage.lagere = lagere;
    }

    public static void setFade(List<Fad> fade) {
        Storage.fade = fade;
    }

    public static void setDestilater(List<Destilat> destilater) {
        Storage.destilater = destilater;
    }

    public static void setMedarbejderne(List<Medarbejder> medarbejderne) {
        Storage.medarbejderne = medarbejderne;
    }

    public static void setVands(List<Vand> vands) {
        Storage.vands = vands;
    }

    public static void setFlasker(List<Flaske> flasker) {
        Storage.flasker = flasker;
    }



    public static void writeStorage() {
        try (FileOutputStream fileOut =
                     new FileOutputStream("SallWhiskySystem/src/storage/storage.txt");
             ObjectOutputStream objOut =
                     new ObjectOutputStream(fileOut)
        ) {
            objOut.writeObject(lagere);
            objOut.writeObject(fade);
            objOut.writeObject(destilater);
            objOut.writeObject(medarbejderne);
            objOut.writeObject(vands);
            objOut.writeObject(flasker);
            Lager.setAntalLagere(lagere.size() + 1);
            Fad.setAntalFade(fade.size() + 1);


            ArrayList<Destillering> Destilleringer = new ArrayList<>();
            ArrayList<Integer> ider = new ArrayList<>();
            for (Destilat destilat : Controller.getDestilater()) {
                if (!ider.contains(destilat.getDestillering().getNewMakeId())) {
                    Destilleringer.add(destilat.getDestillering());
                    ider.add(destilat.getDestillering().getNewMakeId());
                }
            }
            Destillering.setIdMaker(Destilleringer.size() + 1);
            Destilat.setAntalDestilater(destilater.size() + 1);


        } catch (IOException e) {
            System.out.println("Catch in writeStorage");
            throw new RuntimeException(e.getMessage());
        }


    }
}


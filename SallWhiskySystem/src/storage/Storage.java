package storage;

import model.Fad;
import model.Lager;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    static List<Lager> lagere = new ArrayList<>();
    static List<Fad> fade = new ArrayList<>();

    public static void addLager(Lager lager){
        lagere.add(lager);
    }
    public static void removeLager(Lager lager){
        lagere.remove(lager);
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
}

package storage;

import model.Lager;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    static List<Lager> lagere = new ArrayList<>();

    public static void addLager(Lager lager){
        lagere.add(lager);
    }
    public static void removeLager(Lager lager){
        lagere.remove(lager);
    }

    public static List<Lager> getLagere() {
        return lagere;
    }
}

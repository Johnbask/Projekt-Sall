package controller;

import model.Fad;
import model.Lager;
import storage.Storage;

import java.util.List;

public class Controller {
    public static List<Fad> getFade() {
        return Storage.getFade();
    }

    public static List<Lager> getLager() {
        return Storage.getLagere();
    }
}

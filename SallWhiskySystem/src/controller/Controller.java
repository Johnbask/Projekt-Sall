package controller;

import model.Fad;
import storage.Storage;

import java.util.List;

public class Controller {
    public static List<Fad> getFade() {
        return Storage.getFade();
    }
}

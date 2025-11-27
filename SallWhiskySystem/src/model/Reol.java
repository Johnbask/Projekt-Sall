package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reol implements
        java.io.Serializable {

    private int nummer;
    private Map<Integer,Hylde> hylder = new HashMap<>();
    private Lager lager;

    public Reol(int nummer, Lager lager) {
        this.nummer = nummer;
        this.lager = lager;

    }

    public void addHylde(int x){
        hylder.put(x,new Hylde(x,this));
    }

    public void sletHylde(Hylde hylde){
        hylder.remove(hylde.getNummer());
    }

    public Hylde getHylde(int hyldeNummer){
        return hylder.get(hyldeNummer);
    }
}

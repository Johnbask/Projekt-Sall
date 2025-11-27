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

    public int getNummer() {
        return nummer;
    }

    public Map<Integer, Hylde> getHylder() {
        return hylder;
    }

    public Lager getLager() {
        return lager;
    }

    public List<Fad> getFade(){
        List<Fad> reolensfade = new ArrayList<>();
        hylder.forEach((key,Hylde)-> reolensfade.add(Hylde.getFad()));
        //    hylder.forEach((key, hylde) -> reolensfade.add(hylde.getFad()));
        return reolensfade;
    }
}

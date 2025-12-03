package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reol implements
        java.io.Serializable {

    private int nummer;
    // Links
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

    // TODO: Skal muligvis fjernes, eller ændres fra List<Fad> til List<Hylde>.
    //  Har direkte connect til Fad, som ikke burde være der (Ud fra Klassediagrammet)
    public List<Fad> getFade(){
        List<Fad> reolensfade = new ArrayList<>();
        hylder.forEach((key,hylde)->{
                if(hylde.getFad()!=null)reolensfade.add(hylde.getFad());
                }
                );
        return reolensfade;
    }

    @Override
    public String toString() {
        return "Reol " + nummer;
    }
}

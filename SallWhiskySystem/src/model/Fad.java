package model;

import com.sun.scenario.effect.impl.state.AccessHelper;
import storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class Fad implements
        java.io.Serializable {
    public static int antalFade =1;
    private double liter;

    public static void setAntalFade(int antalFade) {
        Fad.antalFade = antalFade;
    }

    private Trætype materiale;
    private int fadId;
    private List<String> tidligereIndhold;
    private String leverandør;

    // links
    // TODO rettelser til List<Class>, instedet for Class class (Fad 1 -- 0..* (1..*) instedet for 1)
    private List<Destilat> gamleDestillater;
    private Destilat destilat;
    private Hylde hylde;
    private List<LageringsHist> lageringsHists= new ArrayList<>();
    private List<Omhældning> omhældning= new ArrayList<>();
    private List<Flaske> flaske = new ArrayList<>();

    public Fad(double liter, Trætype materiale, List<String> tidligereIndhold, String leverandør) {
        this.liter = liter;
        this.materiale = materiale;
        this.fadId = antalFade;
        antalFade++;
        this.tidligereIndhold = tidligereIndhold;
        this.leverandør = leverandør;
    }

    @Override
    public String toString() {
        return
                "fad"+ fadId + " Hylde" + hylde.getNummer() + " reol" + hylde.getReol().getNummer()+" lager"+ hylde.getReol().getLager().getLagerId() ;
    }

    public void setHylde(Hylde hylde) {
        this.hylde = hylde;
    }

    public boolean isEmpty() {
        return destilat == null;
    }

    // Getters
    // Lokalt getters
    public static int getAntalFade() {
        return antalFade;
    }

    public double getLiter() {
        return liter;
    }

    public Trætype getMateriale() {
        return materiale;
    }

    public int getFadId() {
        return fadId;
    }

    public List<String> getTidligereIndhold() {
        return tidligereIndhold;
    }

    public String getLeverandør() {
        return leverandør;
    }

    // Links getters
    public List<Destilat> getGamleDestillater() {
        return gamleDestillater;
    }

    public Destilat getDestilat() {
        return destilat;
    }

    public Hylde getHylde() {
        return hylde;
    }

    public List<LageringsHist> getLageringsHists() {
        return lageringsHists;
    }

    public List<Omhældning> getOmhældning() {
        return omhældning;
    }

    public List<Flaske> getFlaske() {
        return flaske;
    }

    public void setDestilat(Destilat destilat) {
        this.destilat = destilat;
    }

    public void addTidligereIndhold(String s){
        tidligereIndhold.add(s);
    }
}


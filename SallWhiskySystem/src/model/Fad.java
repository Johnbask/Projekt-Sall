package model;

import com.sun.scenario.effect.impl.state.AccessHelper;
import storage.Storage;

import java.time.LocalDate;
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
    private double litterIFad;

    // links
    // TODO rettelser til List<Class>, instedet for Class class (Fad 1 -- 0..* (1..*) instedet for 1)
    private List<Destilat> destillater = new ArrayList<>();
    private Hylde hylde;
    private List<LageringsHist> lageringsHists= new ArrayList<>();
    private List<Omhældning> omhældning= new ArrayList<>();
    private ModningsTid modningsTid;

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

    public boolean isTom() {
        if(destillater == null ||litterIFad <= 0){
            return true;
        }else{
            return false;
        }
    }

    public double getLitterIFad(){
        return litterIFad;
    }
    public void setLitterIFad(double liter){
        litterIFad = liter;
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
        return destillater;
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
    public void addOmhældning(Omhældning omhældning){
        this.omhældning.add(omhældning);
    }

    public void addTidligereIndhold(String s){
        System.out.println(s);
        tidligereIndhold.add(s);
    }
    public Destilat getDestilat(){
        if(destillater.isEmpty()){
            return null;
        }
        return destillater.getLast();
    }

    public void addLagerHist(){
        lageringsHists.add(new LageringsHist(LocalDate.now(),hylde,this));
    }

    public boolean addLiterOfDestilatToFad(double mængde){
        if (litterIFad+mængde<=liter){
            litterIFad+=mængde;
            return true;
        }else {
            return false;
        }
    }

    public void setModningsTid(ModningsTid modningsTid) {
        this.modningsTid = modningsTid;
    }

    private boolean isModen(){
        return modningsTid.isModen();

    }
}


package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fad implements
        java.io.Serializable {
    public static int antalFade =1;
    private double liter;
    private Trætype materiale;
    private int fadId;
    private List<String> tidligereIndhold = new ArrayList<>();
    private String leverandør;
    private double litterIFad;

    // links
    private List<Destilat> destillater = new ArrayList<>();
    private Hylde hylde;
    private List<LageringsHist> lageringsHists= new ArrayList<>();
    private List<Omhældning> omhældning= new ArrayList<>();

    public Fad(double liter, Trætype materiale, List<String> tidligereIndhold, String leverandør) {
        this.liter = liter;
        this.materiale = materiale;
        this.fadId = antalFade;
        antalFade++;
        this.tidligereIndhold.addAll(tidligereIndhold);
        this.leverandør = leverandør;
    }

    public static void setAntalFade(int antalFade) {
        Fad.antalFade = antalFade;
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
        if(destillater.isEmpty() ||litterIFad <= 0){
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

    public List<Destilat> getDestillater() {
        return destillater;
    }

    public boolean addLiterOfDestilatToFad(double mængde){
        if(mængde <= 0){
            return false;
        }
        if (litterIFad+mængde<=liter){
            litterIFad+=mængde;
            return true;
        }else {
            return false;
        }
    }

    public boolean removeLiterOfDestilatFromFad(double mængde){
        if (!(0>liter-mængde)){
            litterIFad-=mængde;
            if (litterIFad==0){
                omhældning=new ArrayList<>();
                destillater=new ArrayList<>();
            }
            return true;
        }else {
            return false;
        }
    }

    public void addDestilat(Destilat destilat){
        destillater.add(destilat);
    }

    public boolean isOnlyHeart(){
        for (Destilat destilat : destillater) {
            if(!destilat.isHeart()) return false;
        }
        return true;
    }

}


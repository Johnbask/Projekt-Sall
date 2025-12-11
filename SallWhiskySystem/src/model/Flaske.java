package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Flaske implements Serializable {

    private Double liter;
    private double alkoholProcent;
    private LocalDate påHældningsDato;
    private String historie;
    private String name;
    private HashSet<Destilat> destilats = new HashSet<>();
    // links
    private Fad fad;
    private Vand vand;

    public Flaske(Double liter, double alkoholProcent, LocalDate påHældningsDato ,String name,Fad fad, Vand vand) {
        this.liter = liter;
        this.alkoholProcent = alkoholProcent;
        this.påHældningsDato = påHældningsDato;
        this.name = name;
        this.fad=fad;
        this.vand=vand;
        this.historie=generateHistory();
        this.destilats.addAll(fad.getDestillater());

    }

    public HashSet<Destilat> getDestilats() {
        return destilats;
    }

    // Getters Lokalt
    public Double getMl() {
        return liter;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public LocalDate getPåHældningsDato() {
        return påHældningsDato;
    }

    public String getHistorie() {
        return historie;
    }
// Getters Links

    public Fad getFad() {
        return fad;
    }

    public Vand getVand() {
        return vand;
    }

    private String generateHistory (){

        StringBuilder s = new StringBuilder();

            s.append("Batch:").append(name);
            s.append(":");
            s.append("\n");


            // finde andel af alle råvare
            List<Destilat> destilats = fad.getDestillater();
            ArrayList<String> råvarene = new ArrayList<>();
            ArrayList<Double> råvareAndel = new ArrayList<>();
            for (Destilat destilat : destilats) {
                int temp;
                // add råvare
                if(!råvarene.contains(destilat.getDestillering().getRåvare())){
                    råvarene.addLast(destilat.getDestillering().getRåvare());
                    // add andel
                    for (Omhældning omhældning : fad.getOmhældning()) {
                        if(omhældning.getDestilat().equals(destilat)) råvareAndel.addLast(omhældning.getMængde());
                    }
                }else{
                    // find index af råvare
                    temp = råvarene.indexOf(destilat.getDestillering().getRåvare());
                    // add andel til det rigtige index
                    for (Omhældning omhældning : fad.getOmhældning()) {
                        if(omhældning.getDestilat().equals(destilat)) råvareAndel.add(temp,råvareAndel.get(temp)+omhældning.getMængde());
                    }
                }
            }
            if(råvarene.size()==1){
                s.append(råvarene.getFirst()+" 100% ");
            }else{
                // finde total
                double total = 0;
                for (Double v : råvareAndel) {
                    total += v;
                }
                // lave andelne til procenter
                for (Double v : råvareAndel) {
                    v = total/v *100;
                }
                // add råvarende med dere procenter
                for (int i = 0; i < råvarene.size(); i++) {
                    s.append(råvarene.get(i));
                    s.append(råvareAndel.get(i));
                    s.append("%");
                    s.append("\n");
                }
            }
        s.append("\n");
            if(fad.isOnlyHeart()) s.append("Fra hjertet");

        s.append("\n");
            if(råvarene.size()==1) s.append("Singel malt");

        s.append("\n");
            s.append(fad.getMateriale().toString());

        s.append("\n");
            if(destilats.size()==1){
                s.append(destilats.getFirst().getRøgmateriale());
            }else{
                List<String> røg = new ArrayList<>();
                List<Double> røgAndel = new ArrayList<>();
                for (Destilat destilat : destilats) {
                    int temp =0;
                    // add røg
                    if(!røg.contains(destilat.getRøgmateriale())){
                        røg.addLast(destilat.getRøgmateriale());
                        // add andel
                        for (Omhældning omhældning : fad.getOmhældning()) {
                            if(omhældning.getDestilat().equals(destilat)) røgAndel.addLast(omhældning.getMængde());
                        }
                    }else {
                        // find index af røg
                        temp = røg.indexOf(destilat.getRøgmateriale());
                        // add andel til det rigtige index
                        for (Omhældning omhældning : fad.getOmhældning()) {
                            if(omhældning.getDestilat().equals(destilat)) røgAndel.add(temp,røgAndel.get(temp)+omhældning.getMængde());
                        }
                    }
                }
                // finde total
                double total = 0;
                for (Double v : røgAndel) {
                    total += v;
                }
                // lave andelne til procenter
                for (Double v : røgAndel) {
                    v = total/v *100;
                }
                // add røgen med dere procenter
                for (int i = 0; i < røg.size(); i++) {
                    s.append(røg.get(i));
                    s.append(røgAndel.get(i));
                    s.append("%");
                }

            }
            Destilat destilat=fad.getDestilat();


        s.append("\n");
            s.append(destilat.getDestillering().getAlkoholProcent()).append("%");

        s.append("\n");
            s.append(vand.getKilde());

        s.append("\n");
            s.append(fad.getOmhældning().getLast().getDato());

        s.append("\n");
            for (Destilat destilatet : fad.getDestillater()) {
                s.append(destilat.getDestillering().getSlutDato());
            }

        return s.toString();
        }

    @Override
    public String toString() {
        return name +" "+ liter +"L  " + alkoholProcent +"%" ;
    }

    }







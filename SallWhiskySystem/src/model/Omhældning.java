package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Omhældning implements Serializable {

    private double mængde;
    private LocalDate dato;

    // link attributter
    private Fad fad;
    private Medarbejder medarbejder;
    private Destilat destilat;

    public Omhældning(double mængde, LocalDate dato, Fad fad, Medarbejder medarbejder, Destilat destilat) {
        this.mængde = mængde;
        this.dato = dato;
        this.fad = fad;
        this.medarbejder = medarbejder;
        this.destilat=destilat;
    }

    public double getMængde() {
        return mængde;
    }

    public LocalDate getDato() {
        return dato;
    }

    public Fad getFad() {
        return fad;
    }

    public Medarbejder getMedarbejder() {
        return medarbejder;
    }

    public boolean isModen(){
        return LocalDate.now().isAfter(dato.plusYears(3));
    }

    public Destilat getDestilat() {
        return destilat;
    }
}

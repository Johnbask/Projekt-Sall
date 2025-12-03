package model;

import java.time.LocalDate;

public class Omhældning {

    private double mængde;
    private LocalDate dato;

    // link attributter
    private Fad fad;
    private Medarbejder medarbejder;

    public Omhældning(double mængde, LocalDate dato, Fad fad, Medarbejder medarbejder) {
        this.mængde = mængde;
        this.dato = dato;
        this.fad = fad;
        this.medarbejder = medarbejder;
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
}

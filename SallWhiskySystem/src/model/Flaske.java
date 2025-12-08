package model;

import java.time.LocalDate;

public class Flaske {

    private  Double ml;
    private  double alkoholProcent;
    private LocalDate påHældningsDato;
    // links
    private Fad fad;
    private Vand vand;

    public Flaske(Double ml, double alkoholProcent, LocalDate påHældningsDato) {
        this.ml = ml;
        this.alkoholProcent = alkoholProcent;
        this.påHældningsDato = påHældningsDato;
    }

    // Getters Lokalt
    public Double getMl() {
        return ml;
    }

    public double getAlkoholProcent() {
        return alkoholProcent;
    }

    public LocalDate getPåHældningsDato() {
        return påHældningsDato;
    }

    // Getters Links

    public Fad getFad() {
        return fad;
    }

    public Vand getVand() {
        return vand;
    }
}

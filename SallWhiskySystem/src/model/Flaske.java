package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Flaske implements Serializable {

    private  Double ml;
    private  double alkoholProcent;
    private LocalDate påHældningsDato;
    private String historie;
    private String name;
    // links
    private Fad fad;
    private Vand vand;

    public Flaske(Double ml, double alkoholProcent, LocalDate påHældningsDato,String historie,String name) {
        this.ml = ml;
        this.alkoholProcent = alkoholProcent;
        this.påHældningsDato = påHældningsDato;
        this.historie = historie;
        this.name = name;
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

    @Override
    public String toString() {
        return name +" "+ ml +"L  " + alkoholProcent +"%" ;
    }
}

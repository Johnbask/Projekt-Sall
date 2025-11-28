package model;

import java.time.LocalDate;

public class Flaske {

    private  Double ml;
    private  double alkoholProcent;
    private LocalDate påHældningsDato;

    public Flaske(Double ml, double alkoholProcent, LocalDate påHældningsDato) {
        this.ml = ml;
        this.alkoholProcent = alkoholProcent;
        this.påHældningsDato = påHældningsDato;
    }
}

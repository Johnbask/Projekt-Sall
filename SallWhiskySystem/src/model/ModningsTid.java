package model;

import java.time.LocalDate;

public class ModningsTid {

    private LocalDate startDato;
    private double Forventespild;
    private boolean isModen;
    private Fad fad;

    public ModningsTid(LocalDate startDato,Fad fad) {
        this.startDato = startDato;
        this.fad = fad;

    }

    public LocalDate getStartDato() {
        return startDato;
    }


    public boolean isModen(){
        return LocalDate.now().isAfter(startDato.plusYears(3));
    }
}

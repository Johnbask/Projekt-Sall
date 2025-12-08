package model;

import java.time.LocalDate;

public class ModningsTid {

    private LocalDate startDato;
    private double Forventespild;
    private boolean isModen;

    public ModningsTid(LocalDate startDato) {
        this.startDato = startDato;

    }

    public LocalDate getStartDato() {
        return startDato;
    }


    public boolean isModen(){
        return LocalDate.now().isAfter(startDato.plusYears(3));
    }
}

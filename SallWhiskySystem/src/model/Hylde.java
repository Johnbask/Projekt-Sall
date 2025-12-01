package model;


public class Hylde implements
        java.io.Serializable {


    private int nummer;
    private Fad fad = null;
    private Reol reol;


    public Hylde(int nummer,Reol reol) {
        this.nummer = nummer;
        this.reol = reol;
    }

    public void addFad(Fad fad){
        this.fad = fad;
    }

    public int getNummer() {
        return nummer;
    }

    public Fad getFad() {
        return fad;
    }

    public void sletFad(){
        this.fad=null;
    }

    public Reol getReol() {
        return reol;
    }

    @Override
    public String toString() {
        return "Hylde " + nummer;
    }
}

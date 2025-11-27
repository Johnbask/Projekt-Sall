package model;

public class Hylde implements
        java.io.Serializable {


    private int nummer;
    private Fad fad;


    public Hylde(int nummer) {
        this.nummer = nummer;
    }

    public void addFad(Fad fad){
        this.fad=fad;
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

}

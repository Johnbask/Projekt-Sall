package model;


public class Hylde implements
        java.io.Serializable {

    private int nummer;
    // Links
    private Fad fad = null;
    private Reol reol;

    public Hylde(int nummer,Reol reol) {
        if (nummer <= 0) {
            throw new IllegalArgumentException("Hylde nummer må ikke være null eller tom");
        }
        if (reol == null) {
            throw new IllegalArgumentException("Reol må ikke være null eller tom");
        }
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

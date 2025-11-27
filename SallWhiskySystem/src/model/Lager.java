package model;

public class Lager implements
        java.io.Serializable {

    private String adresse;
    private int LagerId;
    private  String navn;

    public Lager(String adresse, int lagerId, String navn) {
        this.adresse = adresse;
        LagerId = lagerId;
        this.navn = navn;
    }
}

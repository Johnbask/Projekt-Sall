package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lager implements
        java.io.Serializable {

    private String adresse;
    private int LagerId;
    private  String navn;
    private Map<Integer,Reol> reoler = new HashMap<>();

    public Lager(String adresse, int lagerId, String navn) {
        this.adresse = adresse;
        LagerId = lagerId;
        this.navn = navn;
    }

    public void addReol(int x){
        reoler.put(x,new Reol(x,this));
    }

    public Reol getReol(int x){
        return reoler.get(x);
    }

    public void removeReol(int x){
        reoler.remove(x);
    }





}

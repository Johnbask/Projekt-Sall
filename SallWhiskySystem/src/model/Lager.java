package model;

import com.sun.scenario.effect.impl.state.AccessHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lager implements
        java.io.Serializable {

    public static int AntalLagere=1;

    private String adresse;
    private int LagerId;
    private  String navn;
    private Map<Integer,Reol> reoler = new HashMap<>();

    public Lager(String adresse, String navn) {
        this.adresse = adresse;
        LagerId = AntalLagere;
        AntalLagere++;
        this.navn = navn;
    }

    public void addReol(int x){
        reoler.put(x,new Reol(x,this));
    }

    public Reol getReol(int x){
        return reoler.get(x);
    }

    public Map<Integer, Reol> getReoler() {
        return reoler;
    }

    public void removeReol(int x){
        reoler.remove(x);
    }





}

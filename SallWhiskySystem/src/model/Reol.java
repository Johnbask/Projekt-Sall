package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reol implements
        java.io.Serializable {

    private int nummer;
    private Map<Hylde,Integer> hylder = new HashMap<>();

    public Reol(int nummer) {
        this.nummer = nummer;
    }

    public void addHylde(Hylde hylde){
        hylder.put(hylde,hylde.getNummer());
    }

    public void sletHylde(Hylde hylde){
        hylder.remove(hylde.getNummer());
    }
}

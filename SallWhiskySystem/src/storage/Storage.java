package storage;

import controller.Controller;
import model.Fad;
import model.Hylde;
import model.Lager;
import model.Reol;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Storage {

    static List<Lager> lagere;
    static List<Fad> fade;

    public static void addLager(Lager lager){
        lagere.add(lager);
    }

    public static void removeLager(Lager lager){
        lagere.remove(lager);
    }

    public static List<Lager> getLagere() {
        return lagere;
    }


    public static List<Fad> getFade() {
        return fade;
    }

    public static void addFade(Fad fad) {
        fade.add(fad);
    }

    public static void removeFade(Fad fad) {
        fade.remove(fad);
    }

    public static void  readStorage(){

        try (FileInputStream fileIn =
                     new FileInputStream("SallWhiskySystem/src/storage/storage.txt");
             ObjectInputStream objIn =
                     new ObjectInputStream(fileIn))
        {
            {
                lagere = (ArrayList) objIn.readObject();
                fade = (ArrayList) objIn.readObject();
            }

        } catch (IOException | ClassNotFoundException e) {
            lagere= new ArrayList<>();
            fade=new ArrayList<>();
            System.out.println("shit");

            initialStorageCreation();
            System.out.println("Catch readStorage");

        } catch (Exception e){
            throw new  RuntimeException(e);
        }

    }

    public static void  writeStorage(){
        try (FileOutputStream fileOut =
                     new FileOutputStream("SallWhiskySystem/src/storage/storage.txt");
             ObjectOutputStream objOut =
                     new ObjectOutputStream(fileOut)
        ){
            objOut.writeObject(lagere);
            objOut.writeObject(fade);
        } catch (IOException e) {
            System.out.println("Catch in writeStorage");
            throw new RuntimeException(e.getMessage());
        }


    }



    public  static void initialStorageCreation(){
        // opret lagere
        Lager SHlager = Controller.opretLager("Sønderhøj 30","Det første lager");
        Lager SanderLager = Controller.opretLager("Serup Tinghøjvej 1", "Det bedste Lager");


        // tilføj reoler og hylder til lagerne
        Controller.addReolerTilLager(SHlager,10);
        Controller.addReolerTilLager(SanderLager,4);
        // FindReolPåLager tager et lager og en int, int svare til reolens plads i lagere. Den retunere den Reol
        // addHylderTilReol tilføjere flere hylder til en reol
        Controller.addHylderTilReol(Controller.FindReolPåLager(SHlager,1),5);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SHlager,2),4);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SHlager,3),3);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SHlager,4),7);


        Controller.addHylderTilReol(Controller.FindReolPåLager(SanderLager,1),2);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SanderLager,2),2);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SanderLager,3),2);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SanderLager,4),2);


        int i = 0;
        while (findledigHylde()!= null){
            Controller.opretFad(i,findledigHylde());
            i++;
        }


        Controller.addReolerTilLager(SHlager,1);
        Controller.addReolerTilLager(SanderLager,1);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SHlager,5),7);
        Controller.addHylderTilReol(Controller.FindReolPåLager(SanderLager,5),2);


    }

    // hælpemetode finder ledig hylde
    // retunere null hvis denne ikke findes
    public static Hylde findledigHylde(){
        ArrayList<Reol> reols = new ArrayList<>();
        ArrayList<Hylde> hylders = new ArrayList<>();
        ArrayList<Lager> lagers = new ArrayList<>(Controller.getLager());
        for (Lager lager : lagers) {
            reols.addAll(lager.getReoler().values());
        }
        reols.forEach(( reol)-> hylders.addAll(reol.getHylder().values()));

        for(Hylde hylde : hylders){
            if(hylde.getFad() == null){
                return hylde;
            }
        }

        return null;
    }
}

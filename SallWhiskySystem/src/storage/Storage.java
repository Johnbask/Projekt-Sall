package storage;

import controller.Controller;
import model.*;

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

        Controller.addHylderTilReol(SHlager.getReol(1),5);
        Controller.addHylderTilReol(SHlager.getReol(2),4);
        Controller.addHylderTilReol(SHlager.getReol(3),3);
        Controller.addHylderTilReol(SHlager.getReol(4),7);

        Controller.addHylderTilReol(SanderLager.getReol(1),2);
        Controller.addHylderTilReol(SanderLager.getReol(2),2);
        Controller.addHylderTilReol(SanderLager.getReol(3),2);
        Controller.addHylderTilReol(SanderLager.getReol(4),2);

        Fad fad1=  Controller.opretFad(75, Trætype.WHITEOAK,1,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad2=  Controller.opretFad(75, Trætype.WHITEOAK,2,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad3=  Controller.opretFad(170, Trætype.WHITEOAK,3,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad4=  Controller.opretFad(75, Trætype.WHITEOAK,4,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad5=  Controller.opretFad(125, Trætype.WHITEOAK,5,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad6=  Controller.opretFad(75, Trætype.WHITEOAK,6,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad7=  Controller.opretFad(50, Trætype.WHITEOAK,7,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad8=  Controller.opretFad(75, Trætype.WHITEOAK,8,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad9=  Controller.opretFad(750, Trætype.WHITEOAK,9,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());


        Fad fad10=  Controller.opretFad(70, Trætype.WHITEOAK,10,new ArrayList<>(List.of("Burbon")),"Lille Amerikans Burbon",findledigHylde());
        Fad fad11=  Controller.opretFad(750, Trætype.WHITEOAK,11,new ArrayList<>(List.of("Burbon")),"Enorm Amerikans Burbon",findledigHylde());
        Fad fad12=  Controller.opretFad(100, Trætype.WHITEOAK,12,new ArrayList<>(List.of("Burbon")),"Stor Amerikans Burbon",findledigHylde());

        Fad fad13=  Controller.opretFad(50, Trætype.WHITEOAK,13,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad14=  Controller.opretFad(50, Trætype.WHITEOAK,14,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad15=  Controller.opretFad(50, Trætype.WHITEOAK,15,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad16=  Controller.opretFad(75, Trætype.WHITEOAK,16,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad17=  Controller.opretFad(75, Trætype.WHITEOAK,17,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad18=  Controller.opretFad(75, Trætype.WHITEOAK,18,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad19=  Controller.opretFad(100, Trætype.WHITEOAK,19,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad20=  Controller.opretFad(100, Trætype.WHITEOAK,20,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad21=  Controller.opretFad(100, Trætype.WHITEOAK,21,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());


        Controller.addReolerTilLager(SHlager,1);
        Controller.addReolerTilLager(SanderLager,1);
        Controller.addHylderTilReol(SHlager.getReol(5),7);
        Controller.addHylderTilReol(SanderLager.getReol(5),2);


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

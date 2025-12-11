package gui;

import controller.Controller;
import javafx.application.Application;
import model.*;
import storage.Storage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.*;

public class App {
    public static void main(String[] args) {
        //oprette lager


        try (FileInputStream fileIn =
                     new FileInputStream("SallWhiskySystem/src/storage/storage.txt");
             ObjectInputStream objIn =
                     new ObjectInputStream(fileIn)) {
            {
                Controller.setLager((ArrayList) objIn.readObject());
                Controller.setFade((ArrayList) objIn.readObject());
                Controller.setDestilater((ArrayList) objIn.readObject());
                Controller.setMedarbejder((ArrayList) objIn.readObject());
                Controller.setVand((ArrayList) objIn.readObject());
                Controller.setFlasker((ArrayList) objIn.readObject());




            }

        } catch (IOException | ClassNotFoundException e) {
            initStorage();

        } catch (Exception e) {
            System.out.println("Catch readStorage");
            throw new RuntimeException(e);
        }



        //her starter vi så gui'en
        Application.launch(SallGui.class);

    }

    public static void initStorage(){

        Controller.setLager((new ArrayList<>()));
        Controller.setFade(new ArrayList<>());
        Controller.setDestilater((new ArrayList<>()));
        Controller.setMedarbejder((new ArrayList<>()));
        Controller.setVand((new ArrayList<>()));
        Controller.setFlasker((new ArrayList<>()));



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

        Fad fad1=  Controller.opretFad(75, Trætype.WHITE_OAK,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad2=  Controller.opretFad(75, Trætype.WHITE_OAK,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad3=  Controller.opretFad(170, Trætype.WHITE_OAK,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad4=  Controller.opretFad(75, Trætype.WHITE_OAK,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad5=  Controller.opretFad(125, Trætype.WHITE_OAK,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad6=  Controller.opretFad(75, Trætype.WHITE_OAK,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad7=  Controller.opretFad(50, Trætype.WHITE_OAK,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad8=  Controller.opretFad(75, Trætype.WHITE_OAK,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());
        Fad fad9=  Controller.opretFad(750, Trætype.WHITE_OAK,new ArrayList<>(List.of("Sherry")),"Stor Amerikans Sherry",findledigHylde());


        Fad fad10=  Controller.opretFad(70, Trætype.WHITE_OAK,new ArrayList<>(List.of("Burbon")),"Lille Amerikans Burbon",findledigHylde());
        Fad fad11=  Controller.opretFad(750, Trætype.WHITE_OAK,new ArrayList<>(List.of("Burbon")),"Enorm Amerikans Burbon",findledigHylde());
        Fad fad12=  Controller.opretFad(100, Trætype.WHITE_OAK,new ArrayList<>(List.of("Burbon")),"Stor Amerikans Burbon",findledigHylde());

        Fad fad13=  Controller.opretFad(50, Trætype.WHITE_OAK ,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad14=  Controller.opretFad(50, Trætype.WHITE_OAK,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad15=  Controller.opretFad(50, Trætype.WHITE_OAK,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad16=  Controller.opretFad(75, Trætype.WHITE_OAK,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad17=  Controller.opretFad(75, Trætype.WHITE_OAK,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad18=  Controller.opretFad(75, Trætype.WHITE_OAK,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad19=  Controller.opretFad(100, Trætype.WHITE_OAK,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad20=  Controller.opretFad(100, Trætype.WHITE_OAK,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());
        Fad fad21=  Controller.opretFad(100, Trætype.WHITE_OAK,new ArrayList<>(List.of("Wine","Sherry")),"Small Town distillery",findledigHylde());


        Controller.addReolerTilLager(SHlager,1);
        Controller.addReolerTilLager(SanderLager,1);
        Controller.addHylderTilReol(SHlager.getReol(5),7);
        Controller.addHylderTilReol(SanderLager.getReol(5),2);

        Controller.opretVand("Ungdommens Kilde");
        Controller.opretVand("Mountain Spring");
        Controller.opretMedarbejder("Ruben","Gud");
        Controller.opretMedarbejder("John","Apostel");
        Controller.opretMedarbejder("Sander Frozen","Is prinsesse");
        Destillering destillering = new Destillering(LocalDate.of(2025,12,12),LocalDate.of(2025,12,14),1000,50,Controller.getMedarbejderne().getFirst(),"Malt","Grøn røg","jeg er cool",Controller.getVands().getFirst());
        Destillering destillering2 = new Destillering(LocalDate.of(1913,12,12),LocalDate.of(1913,12,14),1000,50,Controller.getMedarbejderne().getFirst(),"Malt","Grøn røg","jeg er cool",Controller.getVands().getFirst());
        Destillering destillering3 = new Destillering(LocalDate.of(2020,10,10),LocalDate.of(2021,10,10),500,50,Controller.getMedarbejderne().getFirst(),"Korn","Ivory","",Controller.getVands().get(1));
        Destillering destillering4 = new Destillering(LocalDate.of(2000,5,5),LocalDate.of(2000,6,6),500,50,Controller.getMedarbejderne().getFirst(),"Malt","Grøn røg","God urt",Controller.getVands().getFirst());
        Destilat des1 = Controller.opretDestilat(750, true, false, destillering);
        Destilat des2 = Controller.opretDestilat(800, true, true, destillering2);
        Destilat des3 = Controller.opretDestilat(400, true, true, destillering3);
        Destilat des4 = Controller.opretDestilat(500, true, true, destillering4);


        Controller.opretPåhældning(fad19, des1, LocalDate.of(2025, 12, 13), 30, Controller.getMedarbejderne().getFirst());
        Controller.opretPåhældning(fad20, des2, LocalDate.of(1999, 12, 12), 90, Controller.getMedarbejderne().getFirst());
        Controller.opretPåhældning(fad20, des3, LocalDate.of(2024, 11, 11), 5, Controller.getMedarbejderne().getFirst());
        Controller.opretPåhældning(fad20, des4, LocalDate.of(2024, 12, 12), 5, Controller.getMedarbejderne().getFirst());
        Controller.opretPåhældning(fad1, des1, LocalDate.of(2024, 12, 12), 5, Controller.getMedarbejderne().getFirst());
        Controller.opretPåhældning(fad2, des2, LocalDate.of(2024, 12, 12), 5, Controller.getMedarbejderne().getFirst());
        Controller.opretPåhældning(fad3, des3, LocalDate.of(2024, 12, 12), 5, Controller.getMedarbejderne().getFirst());
        Controller.opretPåhældning(fad4, des4, LocalDate.of(2024, 12, 12), 5, Controller.getMedarbejderne().getFirst());


        Flaske flaske1 = Controller.opretFlaske(0.5,42,LocalDate.now().plusMonths(1),"Gyldne Dråber",fad20,Controller.getVands().getFirst());
        Flaske flaske2 = Controller.opretFlaske(0.75,42,LocalDate.now().plusMonths(1),"Dragens elixir",fad1,Controller.getVands().getFirst());
        Flaske flaske3 = Controller.opretFlaske(5,48,LocalDate.now().plusMonths(1),"Mobbe drengen",fad1,Controller.getVands().getFirst());
        Flaske flaske4 = Controller.opretFlaske(0.44,44,LocalDate.now().plusMonths(1),"Jhins favorite",fad1,Controller.getVands().getFirst());






// write storage
 Controller.writeStorage();




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

package storage;

import model.Fad;
import model.Lager;

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
                     new ObjectInputStream(fileIn)
        ) {
            lagere = (ArrayList) objIn.readObject();
            fade = (ArrayList) objIn.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }


    }
}

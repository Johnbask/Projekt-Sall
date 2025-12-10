package gui;

import controller.Controller;
import javafx.application.Application;
import model.Fad;
import model.Hylde;
import model.Lager;
import model.Reol;
import storage.Storage;

import java.util.*;

public class App {
    public static void main(String[] args) {
        //oprette lager
        initStorage();
        //her starter vi så gui'en
        Application.launch(SallGui.class);

    }

    public static void initStorage(){


        Controller.readStorage();




// write storage
 Controller.writeStorage();




    }





}

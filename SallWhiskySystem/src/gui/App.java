package gui;

import controller.Controller;
import javafx.application.Application;

import javax.naming.ldap.Control;

public class App {
    public static void main(String[] args) {
        //oprette lager
        initStorage();
        //her starter vi så gui'en
        Application.launch(SallGui.class);

    }

    public static void initStorage(){


       // Controller.opretLager()

    }
}

package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Medarbejder;
import model.Vand;

import javax.swing.*;
import java.awt.*;

public class MiscPane extends GridPane {

    private final TextField txfVand = new TextField();
    private final TextField txfMedarbejderNavn = new TextField();
    private final TextField txfMedarbejderStilling = new TextField();

    private final Button bVand = new Button("Opret vand");
    private final Button bSletVand = new Button("Slet Vand");
    private final Button bMedarbejder =new Button("Opret Medarbejder");
    private final Button bSletMedarbejder = new Button("Slet Medarbejder");

    private final ListView<Medarbejder> lwMedarbejder = new ListView<>();
    private final ComboBox<Vand> cbxVand = new ComboBox<>();

    private final HBox hBoxmedarbejder = new HBox();



    public MiscPane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        txfVand.setPromptText("Fiji");

        txfMedarbejderNavn.setPromptText("Lars Larsen");
        txfMedarbejderStilling.setPromptText("revisor");

        lwMedarbejder.getItems().setAll(Controller.getMedarbejderne());

        cbxVand.getItems().setAll(Controller.getVands());


        this.add(lwMedarbejder,0,0,2,3);
        this.add(cbxVand,2,0);
        this.add(txfVand,3,0,2,1);
        this.add(bVand,3,1);
        this.add(bSletVand,4,1);

        bVand.setOnAction(actionEvent -> opretVand());
        bSletVand.setOnAction(actionEvent -> sletVand());

        this.add(txfMedarbejderNavn,0,3);
        this.add(txfMedarbejderStilling,0,4);
        this.add(hBoxmedarbejder,0,5);
        hBoxmedarbejder.setSpacing(10);
        hBoxmedarbejder.getChildren().add(bMedarbejder);
        hBoxmedarbejder.getChildren().add(bSletMedarbejder);

        bMedarbejder.setOnAction(actionEvent -> opretMedarbejderAction());
        bSletMedarbejder.setOnAction(actionEvent -> sletMedarbejderAction());










    }

    private void sletMedarbejderAction() {
        if (lwMedarbejder.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("ingen medarbejder valgt");
            alert.showAndWait();
        }else {
           Controller.SletMedarbejder(lwMedarbejder.getSelectionModel().getSelectedItem());
            Controller.writeStorage();
            lwMedarbejder.getItems().setAll(Controller.getMedarbejderne());
        }

    }

    private void opretMedarbejderAction() {
        if (txfMedarbejderNavn.getText().isBlank()||txfMedarbejderStilling.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Giv venlist din medarbejder et navn og en stilling");
            alert.showAndWait();
        }else {
            Medarbejder medarbejder = Controller.opretMedarbejder(txfMedarbejderNavn.getText(),txfMedarbejderStilling.getText());
            lwMedarbejder.getItems().add(medarbejder);
            Controller.writeStorage();
        }

    }

    private void sletVand() {
        if (cbxVand.getSelectionModel().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Intet vand valgt");
            alert.showAndWait();
        }else {
            Controller.sletVand(cbxVand.getSelectionModel().getSelectedItem());
            Controller.writeStorage();
            cbxVand.getItems().setAll(Controller.getVands());
        }

    }

    public void opretVand(){
        if (txfVand.getText().isBlank()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Giv dit vand et navn");
            alert.showAndWait();
        }else {
            Controller.opretVand(txfVand.getText());
            Controller.writeStorage();
            cbxVand.getItems().setAll(Controller.getVands());
        }

    }
}

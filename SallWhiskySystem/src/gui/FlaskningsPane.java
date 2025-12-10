package gui;

import com.sun.javafx.scene.control.DoubleField;
import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import model.*;
import storage.Storage;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class FlaskningsPane extends GridPane {
    public FlaskningsPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        intContent();
    }

    private final TextField tFMakeName = new TextField();
    private final ComboBox<Fad> cbFade = new ComboBox<>();
    private final DoubleField dFLiterFraFad = new DoubleField();
    private final DoubleField dFLiterVand = new DoubleField();
    private final ComboBox<Vand> cbVandKilde = new ComboBox<>();
    private final DoubleField dFFlaskeStørelse = new DoubleField();
    private final DoubleField dFProcent = new DoubleField();
    private final TextArea tAHistorien = new TextArea();
    private final Button bGenrateStory = new Button("Genrate Story");
    private final Button bFlaskkiefy = new Button("Flaskiefy");
    private final Label lLiterIFad = new Label(" ");
    private final Label lError = new Label();
    private final DatePicker dPFlaske = new DatePicker();
    private final Button bSeFlasker = new Button("Se Flasker");

    private void intContent() {

        Label lFlaskning = new Label("Flaskning");
        this.add(lFlaskning, 0, 0, 2, 2);
        Label lHistorie = new Label("Historien");
        this.add(lHistorie, 3, 0, 2, 2);

        this.add(tAHistorien, 3, 3, 1, 8);
        tAHistorien.setEditable(false);
        tAHistorien.setMinWidth(300);
        tAHistorien.setMinHeight(300);

        Label lMakeNavn = new Label("Make name");
        this.add(lMakeNavn, 0, 3);
        this.add(tFMakeName, 1, 3);

        Label lVælgFad = new Label("Vælg Fad");
        this.add(lVælgFad, 0, 4);
        this.add(cbFade, 1, 4);
        cbFade.getItems().setAll(Controller.getModneFade());
        ChangeListener<Fad> listenerFade = (ov, o, n) -> this.selectedFadChanged();
        cbFade.getSelectionModel().selectedItemProperty().addListener(listenerFade);

        this.add(lLiterIFad, 2, 4);


        Label lLiterFraFad = new Label("Liter fra fad");
        this.add(lLiterFraFad, 0, 5);
        this.add(dFLiterFraFad, 1, 5);

        Label lLiterVand = new Label("Liter vand tilføjet i alt");
        this.add(lLiterVand, 0, 6);
        this.add(dFLiterVand, 1, 6);

        Label lVandKilde = new Label("Vandkilde");
        this.add(lVandKilde, 0, 7);
        this.add(cbVandKilde, 1, 7);
        cbVandKilde.getItems().setAll(Controller.getVands());

        Label lFlaskeStørelse = new Label("Flaske størlese L");
        this.add(lFlaskeStørelse, 0, 8);
        this.add(dFFlaskeStørelse, 1, 8);

        Label lProcent = new Label("Alkohold procent");
        this.add(lProcent, 0, 9);
        this.add(dFProcent, 1, 9);

        Label lDatepicker = new Label("Dato : ");
        this.add(lDatepicker, 0, 10);
        this.add(dPFlaske, 1, 10);

        this.add(bGenrateStory, 0, 11);
        bGenrateStory.setOnAction(event -> this.genrateStory());

        this.add(bFlaskkiefy, 1, 11);
        bFlaskkiefy.setOnAction(event -> this.Flaskkiefy());

        this.add(bSeFlasker, 2, 11);
        bSeFlasker.setOnAction(event -> this.SeFlasker());

        this.add(lError, 0, 12, 3, 1);
        lError.setStyle("-fx-text-fill: red;");
        lError.setMinWidth(200);


        dPFlaske.setValue(LocalDate.now());



    }

    private void SeFlasker() {
        FlaskerWindow flaskerWindow = new FlaskerWindow("Flasker");
        flaskerWindow.showAndWait();
    }

    public void updateFade() {

        cbFade.getItems().setAll(Controller.getModneFade());


    }

    private void selectedFadChanged() {
        if (cbFade.getSelectionModel().isEmpty()) {
            lLiterIFad.setText(" ");
        } else {
            cbFade.getSelectionModel().getSelectedItem().getLiter();
            lLiterIFad.setText(cbFade.getSelectionModel().getSelectedItem().getLitterIFad() + "L ");
            lLiterIFad.setText(lLiterIFad.getText() + cbFade.getSelectionModel().getSelectedItem().getDestilat().getDestillering().getAlkoholProcent() + "P");
        }

    }

    private void Flaskkiefy() {
        Flaske flaske = null;

        if (cbFade.getSelectionModel().isEmpty()) {
            lError.setText("ERROR no Fad Selected");
        } else if (cbVandKilde.getSelectionModel().isEmpty()&&(dFLiterVand.getValue()>0)) {
            lError.setText("ERROR ingen vandkilde valgt");
        } else if (tFMakeName.getText().isBlank()) {
            lError.setText("ERROR pick a cool name");
        } else if (dFLiterFraFad.getValue() <= 0 || dFLiterFraFad.getValue() > cbFade.getSelectionModel().getSelectedItem().getLiter()) {
            lError.setText("ERROR pls pick a valid destillat amount to use");
        } else if (dFLiterVand.getValue() <= 0&&!(cbVandKilde.getSelectionModel().isEmpty())) {
            lError.setText("ERROR pls pick a valid water amount to use");
        } else if (dFFlaskeStørelse.getValue() <= 0) {
            lError.setText("ERROR pls pick a valid size to use");
        } else if (dFProcent.getValue() <= 0 || dFProcent.getValue() > 100) {
            lError.setText("ERROR pls pick a valid alkohold procent to use");
        } else if (dPFlaske.getValue().isBefore(cbFade.getSelectionModel().getSelectedItem().getOmhældning().getLast().getDato())) {
            lError.setText("ERROR pls pick a valid date (date before origin date)");
        } else {
            double totalLiter = dFLiterFraFad.getValue() + dFLiterVand.getValue();
            while (totalLiter >= dFFlaskeStørelse.getValue()) {
                if (totalLiter >= dFFlaskeStørelse.getValue()) {
                    flaske = Controller.opretFlaske(dFFlaskeStørelse.getValue(), dFProcent.getValue(), dPFlaske.getValue(), tFMakeName.getText(), cbFade.getSelectionModel().getSelectedItem(), cbVandKilde.getSelectionModel().getSelectedItem());
                    totalLiter -= dFFlaskeStørelse.getValue();
                } else {
                    flaske = Controller.opretFlaske(totalLiter, dFProcent.getValue(), dPFlaske.getValue(), tFMakeName.getText(), cbFade.getSelectionModel().getSelectedItem(), cbVandKilde.getSelectionModel().getSelectedItem());
                    totalLiter -= dFFlaskeStørelse.getValue();
                }
                tAHistorien.setText(flaske.getHistorie());
            }
            cbFade.getSelectionModel().getSelectedItem().removeLiterOfDestilatFromFad(dFLiterFraFad.getValue());
            Controller.writeStorage();
            updateFade();
        }
    }

    private void genrateStory() {
        Flaske flaske=null;
        StringBuilder s = new StringBuilder();
        if (cbFade.getSelectionModel().isEmpty()) {
            lError.setText("ERROR no Fad Selected");
        } else if (dFProcent.getValue() <= 0 || dFProcent.getValue() > 100) {
            lError.setText("ERROR pls pick a valid alkohold procent to use");
        } else if (tFMakeName.getText().isBlank()) {
            lError.setText("ERROR pls name your creation");
        } else if (cbVandKilde.getSelectionModel().isEmpty()&&(dFLiterVand.getValue()>0)) {
            lError.setText("ERROR pls pick a water source");
        } else {
            Fad fad = cbFade.getSelectionModel().getSelectedItem();

            flaske = new Flaske((dFFlaskeStørelse.getValue()), dFProcent.getValue(), dPFlaske.getValue(), tFMakeName.getText(), cbFade.getSelectionModel().getSelectedItem(), cbVandKilde.getSelectionModel().getSelectedItem());



        }
        tAHistorien.setText(flaske.getHistorie());
    }
}

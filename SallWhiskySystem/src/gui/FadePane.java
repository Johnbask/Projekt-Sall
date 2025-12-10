package gui;

import com.sun.javafx.scene.control.IntegerField;
import controller.Controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import model.*;
import storage.Storage;


import java.util.ArrayList;

import java.util.List;


public class FadePane extends GridPane {
    private final Label lblFade = new Label("Fade");

    public FadePane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);


        this.add(lblFade, 0, 0, 2, 1);
        lblFade.setStyle("-fx-font-size: 24px");

        FirstSection();

        SecondSection();

    }

    // fields for first section
    private final TextField tfxUpdate = new TextField();
    private final IntegerField ifFadSøgning = new IntegerField();
    private final Button btnUpdate = new Button("Update Historik");
    private final Button btnSlet = new Button("Slet");
    private final Button bPåfyld = new Button("Påfyldning");
    private  final  TableView<Fad> tvFade = new TableView<>();
    private final Button bOmhældning = new Button("Omhældning");


    private void FirstSection() {


        // Create Columns
        TableColumn<Fad, Integer> colFadID = new TableColumn<>("Fad ID");
        colFadID.setCellValueFactory(new PropertyValueFactory<>("fadId"));

        TableColumn<Fad, List<String>> colTidligereIndhold = new TableColumn<>("Historik");
        colTidligereIndhold.setCellValueFactory(new PropertyValueFactory<>("tidligereIndhold"));



        TableColumn<Fad, Double> colFadStørrelse = new TableColumn<>("Fadstørrelse");
        colFadStørrelse.setCellValueFactory(new PropertyValueFactory<>("liter"));

        TableColumn<Fad, String> colMateriale = new TableColumn<>("Materiale");
        colMateriale.setCellValueFactory(new PropertyValueFactory<>("materiale"));

        TableColumn<Fad, String> colLeverandør = new TableColumn<>("Leverandør");
        colLeverandør.setCellValueFactory(new PropertyValueFactory<>("leverandør"));


    /*    TableColumn<Fad, LocalDate> colKøbtDato = new TableColumn<>("Købt Dato");
        colKøbtDato.setCellValueFactory(new PropertyValueFactory<>("købtdato"));*/

        TableColumn<Fad, String> colLokation = new TableColumn<>("Lokation");
        colLokation.setCellValueFactory(cell -> {
            Fad fad = cell.getValue();

            Hylde hylde = fad.getHylde();
            if (hylde == null) return new SimpleStringProperty("Ingen lokation");

            Reol reol = hylde.getReol();
            Lager lager = (reol != null) ? reol.getLager() : null;

            String hyldeNr = String.valueOf(hylde.getNummer());
            String reolNr = (reol != null) ? String.valueOf(reol.getNummer()) : "?";
            String lagerAdresse = (lager != null) ? lager.getAdresse() : "Ukendt";

            String result = "Hylde: " + hyldeNr +
                    "\nReol: " + reolNr +
                    "\n" + lagerAdresse;

            return new SimpleStringProperty(result);
        });

      //tvFade.getColumns().addAll(colFadID, colTidligereIndhold, colFadStørrelse, colMateriale, colLeverandør, colLokation);

        tvFade.getColumns().add(colFadID);
        tvFade.getColumns().add(colTidligereIndhold);
        tvFade.getColumns().add(colFadStørrelse);
        tvFade.getColumns().add(colMateriale);
        tvFade.getColumns().add(colLeverandør);
       // tvFade.getColumns().add(colKøbtDato);
        tvFade.getColumns().addAll(colLokation);

        tvFade.setMinWidth(700);



        // Test for om det virker TODO Rettelser
        tvFade.getItems().setAll(Controller.getFade());

        // Add it to the pane
        this.add(tvFade, 0, 1, 2, 11);

        // buttons + search field
        this.add(ifFadSøgning, 0, 12);
        ifFadSøgning.setPromptText("Fad ID");
        ifFadSøgning.valueProperty().addListener(observable -> findFadMedId());

        HBox hBox = new HBox();
        hBox.getChildren().addAll(btnUpdate, btnSlet);
        this.add(hBox, 0, 13);
        hBox.setSpacing(10);
        btnSlet.setOnAction(event -> sletFad(tvFade.getSelectionModel().getSelectedItem()));
        this.add(new Label("Søg med FadId"),1,12);

        btnUpdate.setOnAction(event -> updateHistorikAction(tvFade.getSelectionModel().getSelectedItem()));
        tfxUpdate.setPromptText("Sherry");
        this.add(tfxUpdate,0,14);
        this.add(bPåfyld,0,15);
        this.add(bOmhældning,0,16);
        bPåfyld.setMinWidth(250);
        bPåfyld.setOnAction(event -> påFyldninsAction());

        bOmhældning.setMinWidth(250);
        bOmhældning.setOnAction(event -> omhældningsAction());
    }

    private void omhældningsAction() {
        omhældningsVindue omhældningVindue = new omhældningsVindue("Omhældning");
        omhældningVindue.showAndWait();

    }

    private void påFyldninsAction() {

        PåfyldningsVindue påfyldningsVindue = new PåfyldningsVindue("Påfylding");
        påfyldningsVindue.showAndWait();



    }

    private void sletFad(Fad fad) {
        Controller.sletFad(fad);

        Controller.writeStorage();
        System.out.println("deleted"+fad);
        tvFade.getItems().setAll(Controller.getFade());
    }

    private void findFadMedId() {
        List<Fad> fade = new ArrayList<>();
        Controller.getFade().forEach(fad ->
        {if (fad.getFadId()==(ifFadSøgning.getValue())){fade.add(fad);}});
        tvFade.getItems().setAll(fade);
        if (ifFadSøgning.getValue()==0){
            tvFade.getItems().setAll(Controller.getFade());
        }
    }

    // Private Fields for second section
    private final TextField txfHistorik = new TextField();
    private final IntegerField intFadstørrelse = new IntegerField();
    private final ComboBox cbxMateriale = new ComboBox();
    private final TextField txfLeverandør = new TextField();

    private final Button btnOpret = new Button("Opret");
    private final Button btnCancel = new Button("Cancel");

    private void SecondSection() {
        this.add(new Label("Historik:"), 2, 2);
        this.add(txfHistorik, 3, 2);

        this.add(new Label("Fadstørrelse:"), 2, 3);
        this.add(intFadstørrelse, 3, 3);

        this.add(new Label("Materiale: "), 2, 4);
        this.add(cbxMateriale, 3, 4);
        cbxMateriale.getItems().setAll(Trætype.values());

        this.add(new Label("Leverandør:"), 2, 5);
        this.add(txfLeverandør, 3, 5);

        /*
        HBox hBox = new HBox();
        hBox.getChildren().addAll(btnOpret, btnCancel);
        this.add(hBox, 1, 11);
        hBox.setSpacing(50);
         */

        this.add(btnOpret, 2, 10);
        this.add(btnCancel, 3, 10);



        btnOpret.setOnAction(event -> opretFad());
        btnCancel.setOnAction(event -> cancelFad());
    }

    private void cancelFad() {
        intFadstørrelse.setValue(0);
        txfHistorik.clear();
        cbxMateriale.getSelectionModel().clearSelection();
        txfLeverandør.clear();

    }

    private void opretFad() {
        System.out.println(intFadstørrelse.getValue()<0);
        System.out.println(txfLeverandør.getText().isEmpty());
        System.out.println(cbxMateriale.getSelectionModel().isEmpty());





        if (txfLeverandør.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Leverandør skal være udfyldt");
            alert.showAndWait();
            if (alert.getResult().equals(ButtonType.OK)){
                alert.hide();
            }

        }else if (cbxMateriale.getSelectionModel().isEmpty()){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Materiale skal være valgt");
            alert.showAndWait();
            if (alert.getResult().equals(ButtonType.OK)){
                alert.hide();
            }

        }else if (intFadstørrelse.getValue()<=0){

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Fadstørelsen skal være valgt");
            alert.showAndWait();
            if (alert.getResult().equals(ButtonType.OK)){
                alert.hide();
            }

        }else {


            VælgFadVindue vælgFadVindue = new VælgFadVindue("Vælg fad");
            vælgFadVindue.showAndWait();
            Hylde hylde= vælgFadVindue.getHylde();
            vælgFadVindue.close();

            Fad fad = Controller.opretFad((double) intFadstørrelse.getValue(), (Trætype) cbxMateriale.getSelectionModel().getSelectedItem(),new ArrayList<>(List.of(txfHistorik.getText()))
                    ,txfLeverandør.getText(),hylde);
            fad.addLagerHist();
        }


Controller.writeStorage();
tvFade.getItems().setAll(Controller.getFade());


    }


    public void updateHistorikAction(Fad fad){
        if (!tfxUpdate.getText().isEmpty()&&!tfxUpdate.getText().isBlank())
fad.addTidligereIndhold(tfxUpdate.getText());
tvFade.getItems().setAll(Storage.getFade());
Controller.writeStorage();
    }

}

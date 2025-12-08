package gui;

import com.sun.javafx.scene.control.IntegerField;
import controller.Controller;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;





public class OmhældningsVindue extends Stage {

    public OmhældningsVindue(String titel) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(titel);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }


    private final Label lbOmhældning = new Label("Påfyldning");

    private  final  TableView<Fad> tvFade = new TableView<>();
    private final TableView<Destilat> tvDestilater = new TableView<>();

    private final DatePicker datePicker = new DatePicker();
    private final ComboBox<Medarbejder> cbxMedarbejder = new ComboBox<>();
    private final IntegerField intfLiter = new IntegerField();
    private final Button bOmhæld = new Button("Påfyld");
    private final Button bSpild = new Button("Opdater Spild");




    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        pane.add(lbOmhældning, 0, 0, 2, 1);
        lbOmhældning.setStyle("-fx-font-size: 24px");

        // Create Columns
        TableColumn<Fad, Integer> colFadID = new TableColumn<>("Fad ID");
        colFadID.setCellValueFactory(new PropertyValueFactory<>("fadId"));

        TableColumn<Fad, List<String>> colTidligereIndhold = new TableColumn<>("Historik");
        colTidligereIndhold.setCellValueFactory(new PropertyValueFactory<>("tidligereIndhold"));



        TableColumn<Fad, Double> colFadStørrelse = new TableColumn<>("Fadstørrelse");
        colFadStørrelse.setCellValueFactory(new PropertyValueFactory<>("liter"));

        TableColumn<Fad, String> colLiterIfad = new TableColumn<>("Liter I fad");
        colLiterIfad.setCellValueFactory(new PropertyValueFactory<>("litterIFad"));


        tvFade.getColumns().add(colFadID);
        tvFade.getColumns().add(colTidligereIndhold);
        tvFade.getColumns().add(colFadStørrelse);
        tvFade.getColumns().add(colLiterIfad);

        tvFade.setMinWidth(450);



        // Test for om det virker TODO Rettelser
        tvFade.getItems().setAll(Controller.getFade());

        // Add it to the pane
        pane.add(tvFade, 0, 1, 4, 8);
        pane.add(tvDestilater,5,1,2,8);
        tvDestilater.setMinWidth(450);

        // tvdestilater add columns

        tvDestilater.getItems().setAll(Controller.getDestilater());


        TableColumn<Destilat, String> colLiterIDestilat = new TableColumn<>("Liter I fad");
        colLiterIDestilat.setCellValueFactory(new PropertyValueFactory<>("liter"));


        tvDestilater.getColumns().add(colLiterIDestilat);

        TableColumn<Destilat, Boolean> colIsSingleMaLT = new TableColumn<>("Single Malt");
        colIsSingleMaLT.setCellValueFactory(cell ->
        {
            Destilat destilat = cell.getValue();
            return new SimpleBooleanProperty(destilat.getSingleMalt());
        });
        tvDestilater.getColumns().add(colIsSingleMaLT);

        TableColumn<Destilat, String> colIsHeart = new TableColumn<>("Hoved/hale");
        colIsHeart.setCellValueFactory(cell -> {
            Destilat destilat = cell.getValue();
            if (destilat.getHeart()){
                return new SimpleStringProperty("hoved");
            }else {
                return new SimpleStringProperty("hale");
            }

        });
        tvDestilater.getColumns().add(colIsHeart);


        TableColumn<Destilat, String> colLbatchId = new TableColumn<>("Batch Id");
        colLbatchId.setCellValueFactory(new PropertyValueFactory<>("batchId"));
        tvDestilater.getColumns().add(colLbatchId);


        TableColumn<Destilat, String> colSmoke = new TableColumn<>("Røgmateriale");
        colSmoke.setCellValueFactory(new PropertyValueFactory<>("røgmateriale"));
        tvDestilater.getColumns().add(colSmoke);



        // add buttons

        pane.add(datePicker,0,9);
        pane.add(cbxMedarbejder,1,9);
        pane.add(intfLiter,3,9);
        pane.add(new Label("Liter:"),2,9);
        pane.add(bOmhæld,4,9);
        bOmhæld.setOnAction(event -> omhældAction());

        cbxMedarbejder.getItems().setAll(Storage.getMedarbejderne());
        cbxMedarbejder.getSelectionModel().selectFirst();
        datePicker.setValue(LocalDate.now());

        pane.add(bSpild,5,9);
        bSpild.setOnAction(event -> spildAction());








    }

    private void spildAction() {
        int num = intfLiter.getValue();
        Fad fad = tvFade.getSelectionModel().getSelectedItem();
        fad.removeLiterOfDestilatToFad(num);

        tvFade.getItems().setAll(Controller.getFade());
        Controller.writeStorage();


    }

    private void omhældAction() {
        Fad fad = tvFade.getSelectionModel().getSelectedItem();
        Destilat destilat = tvDestilater.getSelectionModel().getSelectedItem();
        LocalDate Date = datePicker.getValue();
        if (intfLiter.getValue()<=0 || destilat==null||fad==null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Mere information er nødvendigt for at foretage en påfyldning");
            alert.showAndWait();

        }else {
            Omhældning omhældning = null;
            omhældning =  Controller.opretOmhældning(fad,destilat,Date,intfLiter.getValue(),cbxMedarbejder.getValue());
            tvFade.getItems().setAll(Controller.getFade());
            tvDestilater.getItems().setAll(Controller.getDestilater());
            Controller.writeStorage();
            if (omhældning==null){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Der er ikke nok plads i fadet eller der er ikke nok destilat tilbage");
                alert.showAndWait();

            }
        }



    }


}

package gui;

import com.sun.javafx.scene.control.IntegerField;
import controller.Controller;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Destilat;
import model.Fad;
import model.Medarbejder;
import model.Omhældning;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class omhældningsVindue extends Stage {

    public omhældningsVindue(String titel) {
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

    private  final TableView<Fad> tvTilFade = new TableView<>();
    private final TableView<Fad> tvFraFade = new TableView<>();

    private final DatePicker datePicker = new DatePicker();
    private final ComboBox<Medarbejder> cbxMedarbejder = new ComboBox<>();
    private final IntegerField intfLiter = new IntegerField();
    private final Button bOmhæld = new Button("Påfyld");;




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


        tvTilFade.getColumns().add(colFadID);
        tvTilFade.getColumns().add(colTidligereIndhold);
        tvTilFade.getColumns().add(colFadStørrelse);
        tvTilFade.getColumns().add(colLiterIfad);

        tvTilFade.setMinWidth(350);



        tvTilFade.getItems().setAll(Controller.getFade());

        // Add it to the pane
        pane.add(tvTilFade, 0, 1, 2, 1);
        pane.add(tvFraFade,3,1,3,1);
        tvFraFade.setMinWidth(450);

        // tvdestilater add columns

        for (Fad fad : Controller.getFade()) {
        if (!fad.isTom()){
            tvFraFade.getItems().add(fad);
        }

        }




        TableColumn<Fad, String> colLiterIDestilat = new TableColumn<>("Liter tilbage af destilat");
        colLiterIDestilat.setCellValueFactory(new PropertyValueFactory<>("litterIFad"));
        tvFraFade.getColumns().add(colLiterIDestilat);


        TableColumn<Fad, Boolean> colIsSingleMaLT = new TableColumn<>("Single Malt");
        colIsSingleMaLT.setCellValueFactory(cell ->
        {
            Fad fad = cell.getValue();
            return new SimpleBooleanProperty(fad.getDestillater().isEmpty() &&fad.getDestilat().getSingleMalt());
        });
        tvFraFade.getColumns().add(colIsSingleMaLT);


        TableColumn<Fad, Boolean> colSmoke = new TableColumn<>("Smoked");
        colSmoke.setCellValueFactory(cell->
        {
            Fad fad=cell.getValue();
            boolean smoked =false;
            for (int i = 0; i < fad.getDestillater().size(); i++) {
                if (fad.getDestillater().get(i).getRøgmateriale()!=null){
                    smoked=true;
                }
            }
            return new SimpleBooleanProperty(smoked);
        });
        tvFraFade.getColumns().add(colSmoke);

        TableColumn<Fad, String> colDato = new TableColumn<>("Seneste omhældnigns dato");
        colDato.setCellValueFactory(Cell-> {
            Fad fad = Cell.getValue();
            return new SimpleStringProperty(fad.getOmhældning().getLast().toString());
        });
        tvFraFade.getColumns().add(colDato);

        TableColumn<Fad, String> colLbatchId = new TableColumn<>("Batch Id");
        colLbatchId.setCellValueFactory(Cell-> {
            Fad fad = Cell.getValue();
            HashSet<Integer> nums = new HashSet<>();
            for (Destilat destilat : fad.getDestillater()) {
                    nums.add(destilat.getBatchId());
            }
            return new SimpleStringProperty(nums.toString());
        });
        tvFraFade.getColumns().add(colLbatchId);


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









    }

    private void spildAction() {
        int num = intfLiter.getValue();
        Fad fad = tvTilFade.getSelectionModel().getSelectedItem();
        fad.removeLiterOfDestilatFromFad(num);

        tvTilFade.getItems().setAll(Controller.getFade());
        Controller.writeStorage();


    }

    private void omhældAction() {
        Fad fadTil = tvTilFade.getSelectionModel().getSelectedItem();
        Fad fadFra = tvFraFade.getSelectionModel().getSelectedItem();
        LocalDate date = datePicker.getValue();
        if (intfLiter.getValue() <= 0 || fadFra == null || fadTil == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Mere information er nødvendigt for at foretage en påfyldning");
            alert.showAndWait();

        } else if (date.isBefore(fadFra.getDestilat().getDestillering().getSlutDato())||date.isBefore(fadFra.getOmhældning().getLast().getDato())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Nye omhældninger skal være efter tidligere omhældninger og efter destilleringen har fundet sted");
            alert.showAndWait();
        } else {


            Omhældning omhældning = null;
            omhældning = Controller.opretOmhældning(fadTil, fadFra, date, intfLiter.getValue(), cbxMedarbejder.getValue());
            tvTilFade.getItems().setAll(Controller.getFade());
            tvFraFade.getItems().clear();
            for (Fad fad : Controller.getFade()) {
                if (!fad.isTom()) {
                    tvFraFade.getItems().add(fad);
                }

                Controller.writeStorage();
                if (omhældning == null) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Der er ikke nok plads i fadet eller der er ikke nok destilat tilbage");
                    alert.showAndWait();

                }
            }


        }


    }
}

package gui;

import com.sun.javafx.scene.control.IntegerField;
import controller.Controller;
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

        TableColumn<Destilat, String> colLiterIDestilat = new TableColumn<>("Liter");
        colLiterIDestilat.setCellValueFactory(new PropertyValueFactory<>("liter"));

        TableColumn<Destilat, String> colIsSingleMaLT = new TableColumn<>("Is single malt");
        colIsSingleMaLT.setCellValueFactory(new PropertyValueFactory<>("isSingleMalt"));


        TableColumn<Destilat, String> colIsHeart = new TableColumn<>("Is Heart");
        colIsHeart.setCellValueFactory(new PropertyValueFactory<>("isHeart"));


        TableColumn<Destilat, String> colLbatchId = new TableColumn<>("Batch Id");
        colLbatchId.setCellValueFactory(new PropertyValueFactory<>("batchId"));

        tvDestilater.getColumns().add(colLiterIDestilat);
        tvDestilater.getColumns().add(colIsSingleMaLT);
        tvDestilater.getColumns().add(colIsHeart);
        tvDestilater.getColumns().add(colLbatchId);

        tvDestilater.getItems().setAll(Controller.getDestilater());





    }



}

package gui;

import com.sun.javafx.scene.control.IntegerField;
import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Fad;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class LagerPane extends GridPane {
    private final ListView<Fad> lWfade = new ListView<>();
    private final IntegerField tFReol = new IntegerField();
    private final IntegerField tFHylde = new IntegerField();
    private final IntegerField tFlager = new IntegerField();
    private final IntegerField  tFad = new IntegerField();
    private final Button bFlytFad = new Button("Flyt Fad");
    private final Button bSletFad = new Button("Slet Fad");

    public LagerPane(){
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        System.out.println("test");

        Label lFade = new Label("Fade");
        this.add(lFade,0,0);

        this.add(tFlager,2,1);
        Label lLager = new Label("Lager id");
        this.add(lLager,1,1);
        tFlager.valueProperty().addListener(observable-> updateLWFade());

        this.add(tFReol,2,2);
        Label lReol = new Label("Reol id");
        this.add(lReol,1,2);
        tFReol.editableProperty().setValue(false);

        this.add(tFHylde,2,3);
        Label lHylde = new Label("Hylde id");
        this.add(lHylde,1,3);
        tFHylde.valueProperty().addListener(observable -> updateLWFade());
        tFReol.editableProperty().setValue(false);


        Label lFad = new Label("Fad id");
        this.add(lFad,1,4);
        this.add(tFad,2,4);
        tFad.valueProperty().addListener(observable -> findFadMedId());

        this.add(lWfade,0,1,1,6);
        ChangeListener<Fad> listenerFade = (ov, o, n) -> this.selectedFadChanged();
        lWfade.getSelectionModel().selectedItemProperty().addListener(listenerFade);

        HBox hBox = new HBox();
        hBox.getChildren().add(bFlytFad);
        hBox.getChildren().add(bSletFad);
       this.add(hBox,0,7);
       hBox.setTranslateX(-10);
       hBox.setSpacing(15);
       hBox.setAlignment(Pos.CENTER);

      //  this.add(bFlytFad,1,7);
        bFlytFad.setDisable(true);
        bFlytFad.setOnAction(event -> flytFadPane(lWfade.getSelectionModel().getSelectedItem()));


    //    this.add(bSletFad,2,7);
        bSletFad.setDisable(true);
        bSletFad.setOnAction(event -> sletFad(lWfade.getSelectionModel().getSelectedItem()));







        updateLWFade();
    }

    private void findFadMedId() {
    List<Fad> fade = new ArrayList<>();
    Controller.getFade().forEach(fad ->
    {if (fad.getFadId()==(tFad.getValue())){fade.add(fad);}});
    lWfade.getItems().setAll(fade);
    if (tFad.getValue()==0){
        lWfade.getItems().setAll(Controller.getFade());
    }
    }



    private void sletFad(Fad fad) {
        Controller.sletFad(fad);
       updateLWFade();
       Controller.writeStorage();

    }

    private void selectedFadChanged() {
        if(lWfade.getSelectionModel().getSelectedItem() != null){
            bFlytFad.setDisable(false);
            bSletFad.setDisable(false);
        }else{
            bFlytFad.setDisable(true);
            bFlytFad.setDisable(true);

        }
    }

    public void updateEditabel(){

        if(!(tFlager.getValue() ==0)){
            tFReol.setEditable(true);
        }
        if (!(tFReol.getValue() ==0)){
            tFHylde.setEditable(true);
        }
        if(tFlager.getValue()==0){
            tFReol.setEditable(false);
            if(!(tFReol.getValue() ==0)) tFReol.setValue(0);
            tFHylde.setEditable(false);
            if(!(tFHylde.getValue() ==0)) tFHylde.setValue(0);
        }
        if(tFReol.getValue()==0){
            tFHylde.setEditable(false);
            if(!(tFHylde.getValue() ==0)) tFHylde.setValue(0);
        }

    }

    private void updateLWFade() {
        updateEditabel();
        if(tFlager.getValue()==0){
            lWfade.getItems().setAll(Controller.getFade());
        } else if (!(tFlager.getValue() ==0) && !(tFReol.getValue() ==0)&& !(tFHylde.getValue() ==0)){
            lWfade.getItems().setAll(Controller.getLager(tFlager.getValue()).getReol((tFReol.getValue())).getHylde(tFHylde.getValue()).getFad());
        } else if (tFHylde.getValue()==0&& !(tFReol.getValue() ==0)) {
            lWfade.getItems().setAll(Controller.getLager(tFlager.getValue()).getReol(tFReol.getValue()).getFade());
        } else if (!(tFlager.getValue() ==0)) {
            lWfade.getItems().setAll(Controller.getLager(tFlager.getValue()).getFade());
        }
        else{
            System.out.println("ERROR");// skal erstats med try catch
        }

    }

    public void flytFadPane(Fad fad){
        FlytFadPane flytFadPane = new FlytFadPane("FlytFad",fad);
        flytFadPane.showAndWait();
        Controller.writeStorage();
    }


}

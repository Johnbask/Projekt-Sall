package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Fad;
import org.w3c.dom.Text;
import storage.Storage;

public class LagerPane extends GridPane {
    private final ListView<Fad> lWfade = new ListView<>();
    private final TextField tFlager = new TextField();
    private final TextField tFReol = new TextField();
    private final TextField tFHylde = new TextField();
    private TextField  tFad = new TextField();
    private final Button bFlytFad = new Button("Flyt Fad");
    private final Button bSletFad = new Button("Slet Fad");
    private final Button bOpretFad = new Button("Opret Fad");

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
        tFlager.textProperty().addListener(observable-> updateLWFade());

        this.add(tFReol,2,2);
        Label lReol = new Label("Reol id");
        this.add(lReol,1,2);
        tFReol.textProperty().addListener(observable-> updateLWFade());
        tFReol.editableProperty().setValue(false);

        this.add(tFHylde,2,3);
        Label lHylde = new Label("Hylde id");
        this.add(lHylde,1,3);
        tFHylde.textProperty().addListener(observable -> updateLWFade());
        tFReol.editableProperty().setValue(false);

        Label lFad = new Label("Fad navn");
        this.add(lFad,1,4);
        this.add(tFad,2,4);
        tFad.textProperty().addListener(observable -> findFadMedNavn());

        this.add(lWfade,0,1,1,6);
        ChangeListener<Fad> listenerFade = (ov, o, n) -> this.selectedFadChanged();
        lWfade.getSelectionModel().selectedItemProperty().addListener(listenerFade);

        HBox hBox = new HBox();
        hBox.getChildren().add(bFlytFad);
        hBox.getChildren().add(bSletFad);
        hBox.getChildren().add(bOpretFad);
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

        bOpretFad.setOnAction(event -> opretFadPane());


        updateLWFade();
    }

    private void findFadMedNavn() {


    }

    private void opretFadPane() {
        OpretFadPane opretFadPane = new OpretFadPane("OpretFad");
        opretFadPane.showAndWait();
    }

    private void sletFad(Fad fad) {
        Controller.sletFad(fad);
       updateLWFade();

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

        if(!tFlager.getText().isBlank()){
            tFReol.setEditable(true);
        }
        if (!tFReol.getText().isBlank()){
            tFHylde.setEditable(true);
        }
        if(tFlager.getText().isBlank()){
            tFReol.setEditable(false);
            if(!tFReol.getText().isBlank()) tFReol.clear();
            tFHylde.setEditable(false);
            if(!tFHylde.getText().isBlank()) tFHylde.clear();
        }
        if(tFReol.getText().isBlank()){
            tFHylde.setEditable(false);
            if(!tFHylde.getText().isBlank()) tFHylde.clear();
        }

    }

    private void updateLWFade() {
        updateEditabel();
        if(tFlager.getCharacters().isEmpty()){
            lWfade.getItems().setAll(Controller.getFade());
        } else if (!tFlager.getCharacters().isEmpty()&&!tFReol.getCharacters().isEmpty()&&!tFHylde.getCharacters().isEmpty()){
            lWfade.getItems().setAll(Controller.getLager(Integer.parseInt(tFlager.getText())).getReol(Integer.parseInt(tFReol.getText())).getHylde(Integer.parseInt(tFHylde.getText())).getFad());
        } else if (tFHylde.getText().isBlank()&&!tFReol.getText().isBlank()) {
            lWfade.getItems().setAll(Controller.getLager(Integer.parseInt(tFlager.getText())).getReol(Integer.parseInt(tFReol.getText())).getFade());
        } else if (!tFlager.getText().isEmpty()) {
            lWfade.getItems().setAll(Controller.getLager(Integer.parseInt(tFlager.getText())).getFade());
        }
        else{
            System.out.println("ERROR");// skal erstats med try catch
        }

    }

    public void flytFadPane(Fad fad){
        FlytFadPane flytFadPane = new FlytFadPane("FlytFad",fad);
        flytFadPane.showAndWait();
    }


}

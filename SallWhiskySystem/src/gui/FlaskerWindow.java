package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Destilat;
import model.Fad;
import model.Flaske;
import model.Lager;

import java.util.ArrayList;
import java.util.List;

public class FlaskerWindow extends Stage {
    public FlaskerWindow(String titel) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(titel);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private final ListView<Flaske> lWFlasker = new ListView<>();
    private final Button bSlet = new Button("Slet");
    private final Button bFindBatch = new Button("find Batch");
    private final ListView<Destilat> lWDestilat = new ListView<>();

    private void initContent(GridPane pane) {

        pane.add(lWFlasker,0,0);
        lWFlasker.getItems().setAll(Controller.getFlasker());
        ChangeListener<Flaske> listenerFlaske = (ov, o, n) -> this.selectedFlaskeChanged();
        lWFlasker.getSelectionModel().selectedItemProperty().addListener(listenerFlaske);

        pane.add(bSlet,0,1);
        bSlet.setOnAction(event -> sletFlaske());

        pane.add(lWDestilat,1,0);
        ChangeListener<Destilat> listenerDestilat = (ov, o, n) -> this.selectedDestilatChanged();
        lWDestilat.getSelectionModel().selectedItemProperty().addListener(listenerDestilat);

        pane.add(bFindBatch,1,1);
        bFindBatch.setOnAction(event -> FindBatch());
        bFindBatch.setDisable(true);

    }

    private void selectedDestilatChanged() {
        if(lWDestilat.getSelectionModel().isEmpty()){
            bFindBatch.setDisable(true);
        }else {
            bFindBatch.setDisable(false);
        }
    }

    private void selectedFlaskeChanged() {
        if(lWFlasker.getSelectionModel().isEmpty()){
            bFindBatch.setDisable(true);
        }else{
            lWDestilat.getItems().setAll(lWFlasker.getSelectionModel().getSelectedItem().getDestilats());
        }
    }

    private void FindBatch() {
        int id = lWDestilat.getSelectionModel().getSelectedItem().getBatchId();
        List<Flaske> batchFlasker = new ArrayList<>();
        List<Flaske> alleFlasker = Controller.getFlasker();
        boolean foundOne = false;
        for (Flaske flaske : alleFlasker) {
            foundOne = false;
            for (Destilat destilat : flaske.getFad().getDestillater()) {
                if(destilat.getBatchId() == id) foundOne = true;
            }
            if(foundOne) batchFlasker.add(flaske);
        }
        lWFlasker.getItems().setAll(batchFlasker);
    }

    private void sletFlaske() {

        if(!lWFlasker.getSelectionModel().isEmpty()){
            Controller.sletFlaske(lWFlasker.getSelectionModel().getSelectedItem());
            lWFlasker.getItems().setAll(Controller.getFlasker());
        }

    }
}

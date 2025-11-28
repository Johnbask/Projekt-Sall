package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Fad;
import model.Hylde;
import model.Lager;
import model.Reol;

public class FlytFadPane extends Stage {

    public FlytFadPane(String titel, Fad fad) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);

        this.setTitle(titel);
        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private final ListView<Lager> lWLager = new ListView<>();
    private final ListView<Reol> lWReol = new ListView<>();
    private final ListView<Hylde> lWHylde = new ListView<>();
    private final Button bFlyt = new Button("Flyt");

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setGridLinesVisible(false);

        Label lFlytFad = new Label("Flyt Fad");
        pane.add(lFlytFad,0,0);

        Label lLager = new Label("Lager");
        pane.add(lLager,0,1);
        pane.add(lWLager,0,2);
        lWLager.getItems().setAll(Controller.getLager());

        Label lReol = new Label("Tomme reoler");
        pane.add(lReol,1,1);
        pane.add(lWReol,1,2);

        Label lHylde = new Label("Tomme Hylder");
        pane.add(lHylde,2,1);
        pane.add(lWHylde,2,2);

    }

}

package gui;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Fad;
import model.Flaske;

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

    private void initContent(GridPane pane) {

        pane.add(lWFlasker,0,0);
        lWFlasker.getItems().setAll(Controller.getFlasker());

        pane.add(bSlet,0,1);
        bSlet.setOnAction(event -> sletFlaske());


    }

    private void sletFlaske() {

        if(!lWFlasker.getSelectionModel().isEmpty()){
            Controller.sletFlaske(lWFlasker.getSelectionModel().getSelectedItem());
            lWFlasker.getItems().setAll(Controller.getFlasker());
        }

    }
}

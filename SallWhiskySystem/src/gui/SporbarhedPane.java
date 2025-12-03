package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.Destilat;
import model.Fad;

public class SporbarhedPane extends GridPane {

    private final Label lblTitle = new Label("Sporbarhed"); // Sporbarhed

    public SporbarhedPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.add(lblTitle, 0, 0);
        lblTitle.setStyle("-fx-font-size: 24px");

        tableView();


    }

    private void tableView() {
        // Create Tableview
        TableView<Fad> tvFade = new TableView<>();

        // Create columns

        // Fad Id.
        TableColumn<Fad, Integer> colFadNr = new TableColumn<>("Fad Nr.");
        colFadNr.setCellValueFactory(new PropertyValueFactory<>("fadId"));

        // Batch Id
     /*   TableColumn<Fad, Integer> colBatchId = new TableColumn<>("Batch Id.");
        colBatchId.setCellValueFactory(cell -> {
            Fad fad = cell.getValue();

            Destilat destilat = Controller
        });*/

    }
}

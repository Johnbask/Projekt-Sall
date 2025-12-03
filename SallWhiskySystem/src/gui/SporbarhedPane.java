package gui;

import controller.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import model.Destilat;
import model.Destillering;
import model.Fad;
import org.w3c.dom.Text;

import java.time.LocalDate;
import java.util.List;

public class SporbarhedPane extends GridPane {

    private final Label lblTitle = new Label("Sporbarhed"); // Sporbarhed

    public SporbarhedPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(true);

        this.add(lblTitle, 0, 0);
        lblTitle.setStyle("-fx-font-size: 24px");

        tableView();

        searchFields();

        historie();
    }

    private void tableView() {
        // Create Tableview
        TableView<Fad> tvFade = new TableView<>();

        // Create columns

        // Fad Id.
        TableColumn<Fad, Integer> colFadNr = new TableColumn<>("Fad Nr.");
        colFadNr.setCellValueFactory(new PropertyValueFactory<>("fadId"));

        // Batch Id
        TableColumn<Fad, Integer> colBatchId = new TableColumn<>("Batch Id.");
        /*
        colBatchId.setCellValueFactory(cell -> {
            Fad fad = cell.getValue();

            Destilat destilat = fad.getDestilat()
        });

         */

        TableColumn<Fad, List<String>> colTidligereIndhold = new TableColumn<>("Tidligere Indhold");
        colTidligereIndhold.setCellValueFactory(new PropertyValueFactory<>("tidligereIndhold"));

        TableColumn<Fad, String> colDestilleringsDato = new TableColumn<>("Destillerings Dato");
        colDestilleringsDato.setCellValueFactory(cell -> {
            Fad fad = cell.getValue();

            LocalDate startDato = fad.getDestilat().getDestillering().getStartDato();

            String result = startDato + "\n";

            return new SimpleStringProperty(result);
        });

        tvFade.getColumns().add(colFadNr);
        tvFade.getColumns().add(colBatchId);
        tvFade.getColumns().add(colTidligereIndhold);
        tvFade.getColumns().add(colDestilleringsDato);

        //tvFade.setItems();

        this.add(tvFade, 0, 1, 2, 6);

    }

    // private fields for searchFields()
    private final TextField txfFadNr = new TextField();
    private final TextField txfBatchId = new TextField();
    private final TextField txfTidligereIndhold = new TextField();
    private final DatePicker dpDestilleringDato = new DatePicker();

    private void searchFields() {
        Label lblSearch = new Label("Søg");
        this.add(lblSearch, 2, 1);
        lblSearch.setStyle("-fx-Font-Size: 16px");

        this.add(new Label("Fad nr."), 2, 2);
        this.add(txfFadNr, 3, 2);

        this.add(new Label("Batch ID:"), 2, 3);
        this.add(txfBatchId, 3, 3);

        this.add(new Label("Tidligere indhold"), 2, 4);
        this.add(txfTidligereIndhold, 3, 4);

        this.add(new Label("Destillerings dato"), 2, 5);
        this.add(dpDestilleringDato, 3, 5);

    }

    private void historie() {
        TextArea txaHistorie = new TextArea();

        this.add(txaHistorie, 0, 7);
        txaHistorie.setPrefSize(300, 250);
    }
}

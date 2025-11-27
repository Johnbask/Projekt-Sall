package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SallGui extends Application {

    @Override
    public void start(Stage stage) {
    stage.setTitle("Sall Whisky");
    BorderPane pane = new BorderPane();
    this.initContent(pane);

    Scene scene = new Scene(pane);
    stage.setScene(scene);
    stage.show();
    }
    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabKonference = new Tab("Storage");
        tabPane.getTabs().add(tabKonference);
        LagerPane lagerPane = new LagerPane();
        tabKonference.setContent(lagerPane);
    }
}

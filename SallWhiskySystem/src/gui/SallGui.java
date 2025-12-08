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


        // Destillering
        Tab tabDestillering = new Tab("Destillering");
        tabPane.getTabs().add(tabDestillering);
        DestilleringPane destilleringPane = new DestilleringPane();
        tabDestillering.setContent(destilleringPane);
        // Påfyldning på Fad

        // Fade
        Tab tabFade = new Tab("Fade");
        tabPane.getTabs().add(tabFade);
        FadePane fadePane = new FadePane();
        tabFade.setContent(fadePane);

        // Lager Tab
        Tab tabLager = new Tab("Lager");
        tabPane.getTabs().add(tabLager);
        LagerPane lagerPane = new LagerPane();
        tabLager.setContent(lagerPane);

        // Færdige Produkter

        // Flaskning
        Tab tabFlaskning = new Tab("Flaskning");
        tabPane.getTabs().add(tabFlaskning);
        FlaskningsPane flaskningsPane = new FlaskningsPane();
        tabFlaskning.setContent(flaskningsPane);
        tabFlaskning.setOnSelectionChanged(event -> flaskningsPane.updateFade());
        // Søgning

        // Sporbarhed Tab
        Tab tabSporbarhed = new Tab("Sporbarhed");
        tabPane.getTabs().add(tabSporbarhed);
        SporbarhedPane sporbarhedPane = new SporbarhedPane();
        tabSporbarhed.setContent(sporbarhedPane);



    }
}

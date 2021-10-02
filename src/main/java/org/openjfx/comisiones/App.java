package org.openjfx.comisiones;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Node;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Login"));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml, ActionEvent event) throws IOException  {
        Node nodo = (Node) event.getSource();
        Stage stage = (Stage) nodo.getScene().getWindow();
        stage.close();
        scene.setRoot(loadFXML(fxml));
        Stage newStage = new Stage();
        newStage.setScene(scene);
        newStage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void main(String[] args) {
        launch();
    }

}
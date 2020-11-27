/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import MediaPlayer.VideoPlayer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author El Pitagoras
 */
public class VideoPrueba extends Application {

    @Override
    public void start(Stage primaryStage) {

        VideoPlayer vd = VideoPlayer.getInstance();

        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((ev) -> {
            System.out.println("Soy un boton");
        });

        Pane root = new VBox();
        root.getChildren().add(btn);
        root.getChildren().add(vd.getVentanaVideo());

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

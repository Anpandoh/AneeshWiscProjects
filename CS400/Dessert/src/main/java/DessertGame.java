//package com.example.dessert;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.application.Platform;
import javafx.geometry.Pos;
import java.util.Random;

public class DessertGame extends Application {

  @Override
  public void start(final Stage stage) {
    // Step 3 & 4
    BorderPane borderPane = new BorderPane();
    Scene scene = new Scene(borderPane, 640, 480);
    stage.setTitle("Dessert in the Desert JavaFX Game");

    // Step 5
    Label scoreLabel = new Label("Score: 0");
    borderPane.setTop(scoreLabel);
    BorderPane.setAlignment(scoreLabel, Pos.TOP_LEFT);

    Button exitButton = new Button("Exit");
    exitButton.setOnAction(event -> {
      Platform.exit();
    });
    borderPane.setBottom(exitButton);
    BorderPane.setAlignment(exitButton, Pos.BOTTOM_RIGHT);

    // Step 6
    Pane pane = new Pane();
    borderPane.setCenter(pane);
    BorderPane.setAlignment(pane, Pos.CENTER);

    // TODO: Step 7-10



    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    Application.launch();
  }
}

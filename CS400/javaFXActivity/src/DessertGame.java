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

  private int score = 0;

  @Override public void start(final Stage stage) {
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
    Random rand = new Random();
    Button buttons[] = new Button[8];
    for (int i = 0; i < buttons.length; i++) {
      Button button = new Button();
      if (i == 0) {
        button.setText("Dessert");
      } else {
        button.setText("Desert");
      }
      button.setOnAction(e -> {
        if (button.getText().equals("Dessert"))
          score++;
        else
          score--;
        scoreLabel.setText("Score: " + score);
        randomizeButtonPositions(rand, buttons);
        exitButton.requestFocus();
      });
      buttons[i] = button;
    }
    randomizeButtonPositions(rand, buttons);
    pane.getChildren().addAll(buttons);


    stage.setScene(scene);
    stage.show();


  }

  private void randomizeButtonPositions(Random random, Button buttons[]) {
    for (Button button: buttons) {
      button.setLayoutX(random.nextInt(601));
      button.setLayoutY(random.nextInt(401));
    }
  }

  public static void main(String[] args) {
    Application.launch();
  }
}

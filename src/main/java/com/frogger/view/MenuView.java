package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MenuView {
    private Stage stage;
    private Runnable startAction;

    public MenuView(Stage stage) {
        this.stage = stage;
    }

    public void setStartAction(Runnable startAction) {
        this.startAction = startAction;
    }

    public void showMenu() {
        VBox menuLayout = new VBox(25);
        menuLayout.setAlignment(Pos.CENTER);

        Button soloButton = createStyledButton("Jouer Solo");
        Button multiplayerButton = createStyledButton("Mode Multijoueur");
        Button quitButton = createStyledButton("Quitter");

        soloButton.setOnAction(e -> startAction.run());
        multiplayerButton.setOnAction(e -> startAction.run());
        quitButton.setOnAction(e -> stage.close());

        menuLayout.getChildren().addAll(soloButton, multiplayerButton, quitButton);

        Scene menuScene = new Scene(menuLayout, 500, 400);
        stage.setScene(menuScene);
        stage.show();
    }

    private Button createStyledButton(String text) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #228B22; -fx-background-radius: 15px; -fx-padding: 10px 20px;");

        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #32CD32; -fx-background-radius: 15px;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #228B22; -fx-background-radius: 15px;"));

        return button;
    }
}

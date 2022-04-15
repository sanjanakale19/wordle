package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("wordle");

        Label l = new Label("Wordle");
        l.setAlignment(Pos.CENTER);
        l.setTextFill(Color.DARKGRAY.darker().darker());
        l.setFont(new Font("", 40));

        VBox box = new VBox();
        box.setPadding(new Insets(40));
        box.setSpacing(20);
        box.setAlignment(Pos.CENTER);

        TextArray arr = new TextArray();
        arr.getGrid().setAlignment(Pos.CENTER);
        VBox box2 = new Keyboard().keyPane;
        box2.setAlignment(Pos.CENTER);

        Label l2 = TextArray.answer;
        l2.setFont(new Font("", 18));
        l2.setTextFill(Color.DARKGREY);
        l2.setAlignment(Pos.CENTER);
        l2.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, new BorderStrokeStyle(null, null, null, 10, 10, null), new CornerRadii(7), new BorderWidths(2))));

//        l2.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(4), null)));
        l2.setMinSize(80, 20);
        l2.setVisible(false);

        box.getChildren().addAll(l, l2, arr.getGrid(), box2);

//        StackPane pane = new StackPane();
//        pane.getChildren().addAll(box, l2);
//        StackPane.setAlignment(l2, Pos.BASELINE_CENTER);

        Scene scene = new Scene(box, 700, 700);
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

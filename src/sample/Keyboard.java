package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Keyboard {
    public static final Key[] board = new Key[29];
    //TODO make this a VBox filled with HBoxes
    public static final VBox keyPane = new VBox();
    static final String[] keys = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "ENTER", "Z", "X", "C", "V", "B", "N", "M", "?", "BACK"};
    public static final List<String> keyList = Arrays.asList(keys);


    public Keyboard() {
//        for (int i = 0; i < 29; i++) {
//            board[i] = new Key(i == 27 ? "ENTER" : i == 28 ? "BACK" : (char) (i == 26 ? 63 : i + 65) + "");
//            keyPane.getChildren().add(i, board[i]);
//        }

        keyPane.setPadding(new Insets(20));
        keyPane.setSpacing(5);
        HBox box1 = new HBox();
        box1.setAlignment(Pos.CENTER);
        box1.setSpacing(5);
        for (int i = 0; i < keys.length; i++) {
            board[i] = new Key(keys[i]);
            box1.getChildren().add(board[i]);

            if (i == 9 || i == 18 || i == 28) {
                keyPane.getChildren().add(box1); // add existing contents of box1
                box1 = new HBox();               // create new box1 for next group of keys
                box1.setAlignment(Pos.CENTER);
                box1.setSpacing(5);
            }
        }


    }
}

class Key extends Button {
    private String val;
    private Color c;

    public Key(String val) {
        super();
        this.val = val;
        this.c = Color.TRANSPARENT;

        // uh idk what this does but change it later
        this.setText(val + "");
        this.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, new CornerRadii(4), null)));
        this.setPadding(new Insets(5, 10, 5, 10));
        // not sure what these two do
//        this.setLayoutX(50);
//        this.setLayoutY(30);

        this.setMinSize(40, 42);

        this.setTooltip(new Tooltip(val + ""));
        this.getTooltip().setFont(new Font("", 10));
        this.setFont(new Font("", 20));
        this.setBorder(new Border(new BorderStroke(Color.DARKGRAY, new BorderStrokeStyle(null, null, null, 10, 10, null), new CornerRadii(7), new BorderWidths(2))));
        setButtonBrightness(this, Color.TRANSPARENT, Color.LIGHTGREY);


        this.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //TODO somehow return val
                TextArray.onClick(val);
//                System.out.println(val);
            }
        });

    }

    public void setColor(int color) {
        c = color == 3 ? TextArray.green : color == 2 ? TextArray.yellow : color == 1 ? Color.LIGHTGRAY : Color.TRANSPARENT;
        setBackground(new Background(new BackgroundFill(c, new CornerRadii(4), null)));
        Key.setButtonBrightness(this, c, c.darker());
    }

    public Color getColor() {
        return c;
    }

    public String getVal() {
        return val;
    }

    static void setButtonBrightness(Button btn, Color background, Color change) {
        btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn.setBackground(new Background(new BackgroundFill(change, new CornerRadii(4), null)));
            }
        });
        btn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                btn.setBackground(new Background(new BackgroundFill(background, new CornerRadii(4), null)));
            }
        });
    }


}
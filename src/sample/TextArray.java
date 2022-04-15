package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class TextArray {
    private static Cell[][] arr = new Cell[6][5];
    private static int row, col;
    private static final String[] word = {"P", "R", "O", "M", "?"};
    private GridPane grid = new GridPane();
    private static final String blank = "";
    private static boolean gameOver = false;
    private static boolean lost = false;
    static final Color green = Color.LIGHTGREEN;    // fix the colors of these two
    static final Color yellow = Color.KHAKI;
    static Label answer = new Label("PROM?");

    public TextArray() {
        row = col = 0;
        grid.setMinWidth(200);
        grid.setHgap(5);
        grid.setVgap(5);



        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j ++) {
                arr[i][j] = new Cell(blank);
                grid.add(arr[i][j], j, i);
            }
        }

    }

    public GridPane getGrid() {
        return grid;
    }

    public void displayArr() {

    }


    public static void onEnter() {
        String word = arr[row][0].getStr() + arr[row][1].getStr() + arr[row][2].getStr()
                + arr[row][3].getStr() + arr[row][4].getStr();

        if (word.equals("PROM?")) {
            //TODO won condition

            onWon();
            gameOver = true;
//            System.out.println("yay go u");
        } else if (row == 5) {
            //TODO lost condition

//            answer.setVisible(true);
//            System.out.println("haha loser");
            lost = true;
            gameOver = true;
        }


            for (int i = 0; i < 5; i++) {
                String str = arr[row][i].getStr();
                Key key = Keyboard.board[Keyboard.keyList.indexOf(str)];
                if (str.equals(TextArray.word[i])) {
                    arr[row][i].setColor(3);  //green for correct
                    key.setColor(3);
                } else if (str.equals(TextArray.word[0]) || str.equals(TextArray.word[1]) || str.equals(TextArray.word[2]) || str.equals(TextArray.word[3]) || str.equals(TextArray.word[4])) {
                    arr[row][i].setColor(2);  //yellow for misplaced
                    if (key.getColor() != green) {
                        key.setColor(2);
                    }
                } else {
                    arr[row][i].setColor(1);  //gray for wrong
                    key.setColor(1);
                }
            }

            if (lost) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                answer.setVisible(true);
            }


    }

    // kinda irrelevant bc sleep does not work until method is returned? so find a way to stagger the letter colors
    public static void onWon() {
        for (int i = 0; i < 5; i++) {
            String str = arr[row][i].getStr();
            Key key = Keyboard.board[Keyboard.keyList.indexOf(str)];

//            tempSleep();    // sleep for a second between color changes of final word

            arr[row][i].setColor(3);  //green for correct
            key.setColor(3);

        }

        answer.setText("(say yes!)");
        answer.setVisible(true);
    }

    static void tempSleep() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onClick(String val) {
        if (gameOver) {
            System.out.println("Game is over");
            return;
        }

        if (val.equals("ENTER")) {
            //TODO if col != 5, throw exception
            if (arr[row][4].getStr().equals(blank)) {

                //doesn't work lol
                answer.setText("not enough letters");
                answer.setVisible(true);
//                tempSleep();
                answer.setVisible(false);

//                System.out.println("dummy enter more characters");
            } else {
                col = 0;
                onEnter();
                row++;
            }
        } else if (val.equals("BACK")) {
            //this all sucks do it again

            // DONE this doesn't work mb have onEnter() catch the exception/disable keyboard??
//            if (col == 5 && row == 5) {     // if you have filled all boxes + still try to hit "BACK"
//                return;
//            }

                String str = arr[row][col].getStr();
//                System.out.println(str);
                if (str.equals(blank) && col != 0) {
                    col--;
                }
                arr[row][col].setStr(blank);
//                System.out.println(arr[row][col].getStr());

//            else if (col == 4) {
//                arr[row][col].setText(blank);
//            } else if (col != 0) {
//                arr[row][col - 1].setText(blank);
//                col--;
//            }

        } else {
            if (arr[row][col].getStr().equals(blank)) {
                arr[row][col].setStr(val);
                //DONE immediately update/refresh cell

                // not sure what the if does bc i forgot but oh well
                if (col == 4) {
                    return;
                }
                else {
                    col++;
                }
            }
        }
    }

}

class Cell extends Label {
    private String str;
    private int color;  // 0 = white, 1 = gray, 2 = yellow, 3 = green

    public Cell(String str) {
        super();
        this.setAlignment(Pos.CENTER);
        this.setMinSize(45, 47);
        this.str = "";
        this.setText(str);
        this.setFont(new Font("monospace", 20));
        this.setPadding(new Insets(5, 10, 5, 10));
        this.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, new BorderStrokeStyle(null, null, null, 10, 10, null), new CornerRadii(7), new BorderWidths(2))));
    }

    public String getStr() {
        return str;
    }

    public void setColor(int color) {
        this.color = color;
        updateCell();
        this.setTextFill(Color.WHITE);
    }

    public void setStr(String str) {
        this.str = str;
        updateCell();
    }

    private void updateCell() {
//        this.setPadding(new Insets(5, 10, 5, 10));
//        this.setMinWidth(20);
        Color newColor = color == 3 ? TextArray.green : color == 2 ? TextArray.yellow : color == 1 ? Color.DARKGRAY : Color.TRANSPARENT;
        this.setBackground(new Background(new BackgroundFill(newColor, new CornerRadii(7), null)));
        // this second call is unnecessary if u can localize borderStroke to change color but aight
        // also resetting it for a non color change call is dumb but go u
        this.setBorder(new Border(new BorderStroke(color != 0 ? newColor : Color.LIGHTGRAY, new BorderStrokeStyle(null, null, null, 10, 10, null), new CornerRadii(7), new BorderWidths(2))));
        this.setText(str);

    }

}

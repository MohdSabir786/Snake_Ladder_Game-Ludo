package com.example.snackladder;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnackLadder extends Application {

    public static final int tileSize =40, height =10,width =10 ;
    int lowerline = tileSize*height;
    int diceValue;
    Label rolledDiceValueLabel;
    Button startGameButton;
    boolean firstPlayerTurn =true, secondPlayerTurn = false, gameStarted = false;
    Player firstPlayer = new Player(tileSize-2, Color.BLACK, "Player 1");
    Player secondPlayer = new Player(tileSize-12,Color.BROWN,"Player 2");
    Pane creatContent(){
        Pane root = new Pane();
        root.setPrefSize(width * tileSize, height*tileSize+100);

//        for 1 tiles size --> root.getChildren().add(new Tile(tileSize));

        for (int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                Tile tile = new Tile(tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                root.getChildren().add(tile);
            }
        }

//        Image on the board
        Image img = new Image("C:\\Users\\hp\\IdeaProjects\\SnackLadder\\src\\main\\snack.jpg");
        ImageView boardImage = new ImageView();
        boardImage.setImage(img);
        boardImage.setFitWidth(tileSize * width);
        boardImage.setFitHeight(tileSize * height);

//        two Buttons for player
        Button playerOneButton = new Button("Player One");
        playerOneButton.setTranslateX(20);
        playerOneButton.setTranslateY(lowerline+20);

        playerOneButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent){
                if(gameStarted){
                    if(firstPlayerTurn){
                        setDiceValue();
                        firstPlayer.movePlayer(diceValue);
                        if(firstPlayer.playerWon() != null){
                            rolledDiceValueLabel.setText(firstPlayer.playerWon());
                            firstPlayerTurn = true;
                            secondPlayerTurn = false;
                            gameStarted = false;
                            startGameButton.setDisable(false);
                            startGameButton.setText("Start Game");

                        }
                        firstPlayerTurn = false;
                        secondPlayerTurn = true;
                    }
                }
            }
        });


        Button playerTwoButton = new Button("Player Two");
        playerTwoButton.setTranslateX(260);
        playerTwoButton.setTranslateY(lowerline+20);

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>(){
            public void handle(ActionEvent actionEvent){
                if(gameStarted){
                    if(secondPlayerTurn){
                        setDiceValue();
                        secondPlayer.movePlayer(diceValue);
                        if(secondPlayer.playerWon() != null){
                            rolledDiceValueLabel.setText(secondPlayer.playerWon());
                            firstPlayerTurn = true;
                            secondPlayerTurn = false;
                            gameStarted = false;
                            startGameButton.setDisable(false);
                            startGameButton.setText("Start Game");

                        }
                        secondPlayerTurn = false;
                        firstPlayerTurn  = true;
                    }
                }
            }
        });

        startGameButton = new Button("Start Game");
        startGameButton.setTranslateX(130);
        startGameButton.setTranslateY(lowerline+50);

        startGameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStarted = true;
                startGameButton.setText("Ongoing Game");
                startGameButton.setDisable(true);
                secondPlayer.initilize();
                firstPlayer.initilize();
            }
        });

        rolledDiceValueLabel = new Label("Start the Game");
        rolledDiceValueLabel.setTranslateX(130);
        rolledDiceValueLabel.setTranslateY(lowerline+20);

        root.getChildren().addAll(boardImage, playerOneButton, playerTwoButton,
                firstPlayer.getCoin(), secondPlayer.getCoin(),
                 rolledDiceValueLabel, startGameButton
        );
        return root;
    }
    private void setDiceValue(){
        diceValue = (int)(Math.random()*6+1);
        rolledDiceValueLabel.setText("Dice Value : "+diceValue);
    }
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(creatContent());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
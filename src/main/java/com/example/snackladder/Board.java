package com.example.snackladder;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    private ArrayList<Pair<Integer,Integer>> positionCoordinates;
    private ArrayList<Integer> snakeLadderPosition;
    public Board(){
        populatePositionCoordinates();
        populateSnakeLadderPosition();
    }
    private void populatePositionCoordinates(){
        positionCoordinates = new ArrayList<>();
        positionCoordinates.add(new Pair<Integer,Integer>(0,0));
        int x=1,y=10,xPos,yPos;
        for(int i=0; i<SnackLadder.height; i++){
            x=1;
            for(int j=0; j<SnackLadder.width; j++){
                if(y%2==0)
                    xPos = x * SnackLadder.tileSize - SnackLadder.tileSize/2;
                else
                    xPos = SnackLadder.width * SnackLadder.tileSize -(x * SnackLadder.tileSize - SnackLadder.tileSize/2);

                yPos = y * SnackLadder.tileSize - SnackLadder.tileSize/2;
                positionCoordinates.add(new Pair<Integer,Integer>(xPos,yPos));
                x++;
            }
            y--;
        }
    }

    private void populateSnakeLadderPosition(){
        snakeLadderPosition = new ArrayList<>();
        for(int i=0; i<101; i++){
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(4,14);
        snakeLadderPosition.set(9,31);
        snakeLadderPosition.set(21,42);
        snakeLadderPosition.set(28,84);
        snakeLadderPosition.set(51,67);
        snakeLadderPosition.set(72,91);
        snakeLadderPosition.set(80,91);
        snakeLadderPosition.set(80,99);
        snakeLadderPosition.set(98,79);
        snakeLadderPosition.set(95,75);
        snakeLadderPosition.set(93,73);
        snakeLadderPosition.set(87,36);
        snakeLadderPosition.set(62,19);
        snakeLadderPosition.set(64,60);
        snakeLadderPosition.set(54,34);
        snakeLadderPosition.set(17,7);
    }
    public int getXCoordinate(int position){
        return positionCoordinates.get(position).getKey();
    }
    public int getYCoordinate(int position){
        return positionCoordinates.get(position).getValue();
    }
    public int getNextPosition(int position){
        if(position >= 1 && position <=100)
             return snakeLadderPosition.get(position);
        else return -1;
    }


}

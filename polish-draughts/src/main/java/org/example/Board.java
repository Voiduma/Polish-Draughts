package org.example;

import java.util.Objects;

public class Board {
    int size;
    Pawn[][] pawnFields;
    public Board (int boardSize){
        this.size=boardSize;
        this.pawnFields= new Pawn[boardSize][boardSize];
        for(int i=0;i<(size/2)-1;i++){
            if(i%2==1) {
                for (int j = 0; j < size; j++) {
                    if (j % 2 == 0) {
                        pawnFields[i][j] = new Pawn("B", i, j, false);
                    }
                }
            }
            if(i%2==0) {
                for (int j = 0; j < size; j++) {
                    if (j % 2 == 1) {
                        pawnFields[i][j] = new Pawn("B", i, j, false);
                    }
                }
            }
        }
        for(int i=(size/2)+1;i<size;i++){
            if(i%2==1) {
                for (int j = 0; j < size; j++) {
                    if (j % 2 == 0) {
                        pawnFields[i][j] = new Pawn("W", i, j, false);
                    }
                }
            }
            if(i%2==0) {
                for (int j = 0; j < size; j++) {
                    if (j % 2 == 1) {
                        pawnFields[i][j] = new Pawn("W", i, j, false);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        String stringResult = "";
        char c='A';
        System.out.print(" _|");
        for(int i=0;i<size;i++){
            System.out.print(c+"|");
            c++;
        }
        System.out.print("\n");
        for(int i=0;i<size;i++){
            if(i+1<=9){
                stringResult+=" "+(i+1);
            }
            else {
                stringResult+=i+1;
            }
            for(int j=0;j<size;j++){
                stringResult+="|";
                if(pawnFields[i][j]==null){
                    stringResult+="_";
                }else{
                    stringResult+=pawnFields[i][j].color;
                }
            }
            stringResult+="|";
            stringResult+="\n";
        }
        return  stringResult;
    }

    public String isMoveValid(int row1, int col1, int row2, int col2, String color){
        String oppositeColor = "";
        if (Objects.equals(color, "B")) { oppositeColor="W";} else if(Objects.equals(color, "W")){oppositeColor="B";}
        if (pawnFields[row2][col2]==null &&(
            (row1+1==row2)&&(col1+1==col2) ||
            (row1-1==row2)&&(col1+1==col2) ||
            (row1+1==row2)&&(col1-1==col2) ||
            (row1-1==row2)&&(col1-1==col2)
        )){
            movePawn(row1, col1, row2, col2);
            return "move";
        }
        else if (pawnFields[row2][col2]==null){
            if (row2==row1+2 && col2==col1+2 && Objects.equals(pawnFields[row1+1][col1+1].color, oppositeColor)){
                movePawn(row1, col1, row2, col2);
                removePawn(row1+1,col1+1);
                return "strike";
            }
            else if (row2==row1-2 && col2==col1-2 && Objects.equals(pawnFields[row1-1][col1-1].color, oppositeColor)){
                movePawn(row1, col1, row2, col2);
                removePawn(row1-1,col1-1);
                return "strike";
            }
            else if (row2==row1-2 && col2==col1+2 && Objects.equals(pawnFields[row1-1][col1+1].color, oppositeColor)){
                movePawn(row1, col1, row2, col2);
                removePawn(row1-1,col1+1);
                return "strike";
            }
            else if (row2==row1+2 && col2==col1-2 && Objects.equals(pawnFields[row1+1][col1-1].color, oppositeColor)){
                movePawn(row1, col1, row2, col2);
                removePawn(row1+1,col1-1);
                return "strike";
            }
            else {
                return "invalid";
            }
        } else {
            return "invalid";
        }
    }

    public enum moveStatus{
        STRIKE, MOVE, INVALID
    }

    public void removePawn(int row, int col){
        pawnFields[row][col]=null;
    }

    public void movePawn(int row1, int col1, int row2, int col2){
        String color=pawnFields[row1][col1].color;
        boolean isCrowned=pawnFields[row1][col1].isCrowned;
        pawnFields[row2][col2]=new Pawn(color, row2, col2, isCrowned);
        removePawn(row1,col1);
    }
}

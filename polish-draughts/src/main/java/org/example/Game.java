package org.example;
import java.util.Objects;
import java.util.Scanner;

public class Game {
    /*Scanner input = new Scanner(System.in);
    System.out.println("Enter board size: ");
    String n = input.nextLine();*/
    int n=10;
    boolean gameFinished=false;
    int playerTurn=1;
    public void start(){
        Board board = new Board(n);
        System.out.println(board);

        String displayColor = "white";
        String checkedColor = "";
        while(!gameFinished){
            if (playerTurn==2){
                checkedColor="B";
                displayColor="black";
            }else if (playerTurn==1){
                checkedColor="W";
                displayColor="white";
            }

            System.out.println("Player "+playerTurn+" choose "+displayColor+" pawn to move (number & letter lowercase): ");
            Scanner sc= new Scanner(System.in);
            String chosenField= sc.nextLine();
            int row1=Integer.parseInt(chosenField.split("")[0])-1;
            char cordLetter=chosenField.split("")[1].charAt(0);
            int col1 = cordLetter-'a';

            //noinspection DuplicateCondition
            if(board.pawnFields[row1][col1]==null || !Objects.equals(board.pawnFields[row1][col1].color, checkedColor)){
                System.out.println("Invalid field, try again");
                continue;
            } else if(Objects.equals(board.pawnFields[row1][col1].color, checkedColor)){
                System.out.println("Choose where to move the pawn (number & letter lowercase): ");
                chosenField= sc.nextLine();
                int row2=Integer.parseInt(chosenField.split("")[0])-1;
                cordLetter=chosenField.split("")[1].charAt(0);
                int col2 = cordLetter-'a';

                switch (board.isMoveValid(row1, col1, row2, col2, checkedColor)){
                case "move":
                    System.out.println("Pawn moved from: "+row1+col1+" to: "+row2+col2);
                    break;
                case "strike":
                    System.out.println("Pawn moved from: "+row1+col1+" to: "+row2+col2+" strike occurred");
                    break;
                case "invalid":
                    System.out.println("Invalid field, try again");
                    continue;
                }

            }
            if (playerTurn==1){playerTurn=2;}else if(playerTurn==2){playerTurn=1;}
            System.out.println(board);
        }
    }
}

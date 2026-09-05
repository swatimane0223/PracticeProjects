package org.LLDProjects.tictactoe.modal;

import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.List;

public class Board {

    public int size;
    public PlayingPiece[][] board;

    public Board(int size){
        this.size= size;
        this.board= new PlayingPiece[size][size];
    }

    public void printBoard(){
        for(int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                if(board[i][j]!=null){
                    System.out.print(board[i][j].pieceType.name() + "   ");
                }
                else {
                    System.out.print("    ");
                }
                System.out.print(" | ");
            }
            System.out.println();
        }
    }

    public List<Position> getFreeCells(){
        List<Position> freeCells= new ArrayList<>();

        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                if (board[i][j]==null){
                    freeCells.add(new Position(i,j));
                }
            }
        }
        return freeCells;
    }

    public Boolean addPiece(int i,  int j, PlayingPiece piece){
        if(board[i][j]!=null){
            return false;
        }
        board[i][j]=piece;
        return true;
    }
}

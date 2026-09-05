package org.LLDProjects.tictactoe;

import org.LLDProjects.tictactoe.modal.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TicTacToeGame {

    Deque<Player> players;
    Player winner;
    Board board;

    void initializeGame(){
        players = new LinkedList<>();
        PlayingPieceX xPiece= new PlayingPieceX();
        Player player1 =new Player("player1", xPiece);
        players.add(player1);

        PlayingPieceO oPiece= new PlayingPieceO();
        Player player2 = new Player("player2", oPiece);
        players.add(player2);

        //Initialise size of board
        System.out.println("enter size");
        Scanner scanner = new Scanner(System.in);
        int size= scanner.nextInt();

        board= new Board(size);
    }

    public GameStatus startGame() {

        boolean noWinner = true;
        while (noWinner) {
            // Remove the player whose turn is and also put the player in the list back
            Player currentPlayer= players.removeFirst();

            // Get the free space from the board
            board.printBoard();
            List<Position> freeSpace= board.getFreeCells();
            if(freeSpace.isEmpty()){
                noWinner=false;
                continue;
            }

            // Read the user input
            System.out.print("Player: " + currentPlayer.name + " - Please enter [row, column]: ");
            Scanner scanner = new Scanner(System.in);
            String s =  scanner.nextLine();
            String[] values= s.split(",");
            int inputRow = Integer.valueOf(values[0]);
            int inputColumn= Integer.valueOf(values[1]);

            // Place the piece in the board
            boolean validMove= board.addPiece( inputRow, inputColumn, currentPlayer.piece);
            if (!validMove) {
                // Invalid Move: Player can not insert the piece into this cell, player has to choose another cell
                System.out.println("Incorrect position chosen, try again!");
                // Add the player back to the queue(in the front)
                players.addFirst(currentPlayer);
                continue;
            }
            // Add the player to the end of the queue
            players.addLast(currentPlayer);

            // Check if the valid move is a winning move or not
            boolean isWinner= checkForWinner(inputRow, inputColumn, currentPlayer.piece.pieceType);
            if(isWinner){
                board.printBoard();
                winner=currentPlayer;
                return GameStatus.WIN;
            }
        }
        return GameStatus.DRAW;

    }

    private Boolean checkForWinner(int row, int column, PieceType pieceType) {

        boolean rowMatch= true;
        boolean columnMatch= true;
        boolean diagonalMatch= true;
        boolean antiDiagonalMatch= true;

        // Check Row
        for (int i = 0; i < board.size; i++) {
            if (board.board[row][i] == null || board.board[row][i].pieceType != pieceType) {
                rowMatch = false;
                break;
            }
        }

        // Check Column
        for (int i = 0; i < board.size; i++) {
            if (board.board[i][column] == null || board.board[i][column].pieceType != pieceType) {
                columnMatch = false;
                break;
            }
        }

        // Check Diagonally
        for (int i = 0, j = 0; i < board.size; i++, j++) {
            if (board.board[i][j] == null || board.board[i][j].pieceType != pieceType) {
                diagonalMatch = false;
                break;
            }
        }

        // Check Anti-Diagonally
        for (int i = 0, j = board.size - 1; i < board.size; i++, j--) {
            if (board.board[i][j] == null || board.board[i][j].pieceType != pieceType) {
                antiDiagonalMatch = false;
                break;
            }
        }

        return rowMatch || columnMatch || diagonalMatch || antiDiagonalMatch;

    }


}

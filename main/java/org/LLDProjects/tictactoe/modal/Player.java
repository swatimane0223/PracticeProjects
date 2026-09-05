package org.LLDProjects.tictactoe.modal;

public class Player {

    public String name;
    public PlayingPiece piece;

    public Player(String name, PlayingPiece piece){
        this.name=name;
        this.piece=piece;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayingPiece getPiece() {
        return piece;
    }

    public void setPiece(PlayingPiece piece) {
        this.piece = piece;
    }
}

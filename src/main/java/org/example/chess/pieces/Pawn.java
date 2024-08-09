package org.example.chess.pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.LinkedList;

public class Pawn extends Piece {
    public Image image;
    public ImageView imageView;
    LinkedList<Piece> pieces;
    private int[] validMoves;
    private boolean hasMoved = false;

    public Pawn(int row, int col, boolean side, LinkedList<Piece> pieces) {
        this.row = row;
        this.col = col;
        this.isWhite = side;
        this.pieces = pieces;

        if (isWhite) { // White
            image = new Image(String.valueOf(this.getClass().getResource("/pieces/pl.png")));
        } else {
            image = new Image(String.valueOf(this.getClass().getResource("/pieces/pd.png")));
        }
        imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);

        pieces.add(this);

        if (isWhite) {
            validMoves = new int[]{-8, -16};
        } else {
            validMoves = new int[]{8, 16};
        }
    }

    public Image getImage() {
        return image;
    }

    public void setMoved() {
        this.hasMoved = true;
        if (isWhite) {
            validMoves = new int[]{-8};
        } else {
            validMoves = new int[]{8};
        }
    }

    public int[] getValidMoves() {
        return validMoves;
    }
}

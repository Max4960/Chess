package org.example.chess.pieces;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.LinkedList;

public class King extends Piece {
    public Image image;
    public ImageView imageView;
    LinkedList<Piece> pieces;
    private int[] validMoves = {-8, -1, 1, 8, -7, -9, 7, 9};

    public King(int row, int col, boolean side, LinkedList<Piece> pieces) {
        this.row = row;
        this.col = col;
        this.isWhite = side;
        this.pieces = pieces;

        if (isWhite) { // White
            image = new Image(String.valueOf(this.getClass().getResource("/pieces/kl.png")));
        } else {
            image = new Image(String.valueOf(this.getClass().getResource("/pieces/kd.png")));
        }
        imageView = new ImageView(image);
        imageView.setFitHeight(80);
        imageView.setFitWidth(80);

        pieces.add(this);
    }

    public Image getImage() {
        return image;
    }

    public int[] getValidMoves() {
        return validMoves;
    }
}

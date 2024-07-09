package org.example.chess.pieces;

import javafx.scene.image.Image;

public abstract class Piece {
    protected boolean isWhite;
    protected int row, col;

    public abstract Image getImage();

    public abstract int[] getValidMoves();


}

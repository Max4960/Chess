package org.example.chess;

import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.example.chess.pieces.Piece;

public class Tile extends Canvas {
    private int row;
    private int col;
    private int pos;
    private Piece piece;
    private boolean isOccupied = false;

    private int size = 80;

    private Color BACKGROUND_COLOR = Color.web("#b5b5b5");
    private Color ALTERNATE_COLOR = Color.web("#636363");

    public Tile(int row, int col, int pos, Game game) {
        this.row = row;
        this.col = col;
        this.pos = pos;

        setWidth(size);
        setHeight(size);

        paint();

        setOnMouseClicked(mouseEvent -> {
            game.handleTileClick(pos);
        });
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        var gc = getGraphicsContext2D();
        if (piece != null) {
            gc.drawImage(piece.getImage(), 0, 0, 80, 80);
            isOccupied = true;
        } else {
            paint();
            isOccupied = false;
        }

    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isOccupied(){
        return isOccupied;
    }

    public void paint() {
        if ((row + col) % 2 == 0) {
            paintColor(ALTERNATE_COLOR);
        } else {
            paintColor(BACKGROUND_COLOR);
        }
    }

    private void paintColor(Paint color) {
        var gc = getGraphicsContext2D();
        gc.clearRect(0,0, getWidth(), getHeight());
        gc.setGlobalAlpha(1.0);
        gc.setFill(color);
        gc.fillRect(0,0, size, size);
        gc.setFill(Color.BLACK);
        gc.fillText(pos + " (" + col + "," + row + ")", getWidth() / 2, getHeight() / 2);
    }

    public void highlight() {
        var gc = getGraphicsContext2D();
        gc.setFill(Color.LIGHTYELLOW);
        gc.setGlobalAlpha(0.5);
        gc.fillRect(0, 0, 80, 80);
    }

    public void unhighlight() {
        var gc = getGraphicsContext2D();
        gc.clearRect(0,0, getWidth(), getHeight());
        paint();
        setPiece(piece);
    }

    public int getPosition() {
        return pos;
    }
}

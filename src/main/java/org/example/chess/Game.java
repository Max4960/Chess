package org.example.chess;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.example.chess.pieces.*;

import java.util.HashMap;
import java.util.LinkedList;

public class Game extends Application {

    private GridPane grid;
    public LinkedList<Piece> pieces = new LinkedList<>();
    public HashMap<Integer, Tile> tiles = new HashMap<>();

    private boolean moving = false;
    private Piece currentPiece;
    private Tile currentTile;


    @Override
    public void start(Stage stage) throws Exception {
        grid = new GridPane();
        int pos = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Tile tile = new Tile(row, col, pos, this);
                grid.add(tile, col, row);
                tiles.put(pos, tile);
                pos++;
            }
        }

        Scene scene = new Scene(grid, 640, 640);
        stage.setTitle("Chess");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        initialise("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR");
    }

    public void initialise(String fen) {
        int row = 0;
        int col = 0;
        int pos = 0;
        boolean skipped = false;
        boolean isWhite = false;
        for (char c : fen.toCharArray()) {
            if (c == '/') {
                col = 0;
                row++;
            } else {
                if (Character.isDigit(c)) {
                    col += (int) c;
                } else {
                    if (Character.isUpperCase(c)) { // White piece
                        isWhite = true;
                        if (!skipped) {
                            pos += 32;
                            skipped = true;
                        }
                    }
                    switch (c) {
                        case 'r':
                        case 'R':
                            Rook r = new Rook(row, col, isWhite, pieces);
                            tiles.get(pos).setPiece(r);
                            break;
                        case 'n':
                        case 'N':
                            Knight n = new Knight(row, col, isWhite, pieces);
                            tiles.get(pos).setPiece(n);
                            break;
                        case 'b':
                        case 'B':
                            Bishop b = new Bishop(row, col, isWhite, pieces);
                            tiles.get(pos).setPiece(b);
                            break;
                        case 'q':
                        case 'Q':
                            Queen q = new Queen(row, col, isWhite, pieces);
                            tiles.get(pos).setPiece(q);
                            break;
                        case 'k':
                        case 'K':
                            King k = new King(row, col, isWhite, pieces);
                            tiles.get(pos).setPiece(k);
                            break;
                        case 'p':
                        case 'P':
                            Pawn p = new Pawn(row, col, isWhite, pieces);
                            tiles.get(pos).setPiece(p);
                            break;
                    }
                    pos++;
                }
                col++;

            }
        }
    }

    private boolean checkValidMove(int pos) {
        int[] moves = currentPiece.getValidMoves();
        int currentPosition = currentTile.getPosition();

        if (currentPiece instanceof Pawn) {
            if (currentPosition + moves[0] == pos) {
                return true;
            }
            return false;
        } else if (currentPiece instanceof Knight) {
            for (int move : moves) {
                if (currentPosition + move == pos) {
                    return true;
                }
            }
            return false;
        }

        for (int i = 1; i <= 8; i++) {
            for (int move : moves) {
                if (currentPosition + (move * i) == pos) {
                    System.out.println("Valid");
                    return true;
                }
            }
        }
        return false;
    }

    public void handleTileClick(int pos) {
        if (moving && currentTile != tiles.get(pos) && checkValidMove(pos)) {
            System.out.println("Tile Moved " + pos);
            tiles.get(pos).highlight();
            if (tiles.get(pos).getPiece() != null) {
                tiles.get(pos).setPiece(null);
            }
            tiles.get(pos).setPiece(currentPiece);
            moving = false;
            currentPiece = null;
            currentTile.setPiece(null);
            currentTile = tiles.get(pos);
        } else if (tiles.get(pos).isOccupied()) {
            System.out.println("Tile Clicked " + pos);
            if (currentTile != null) {
                currentTile.unhighlight();
                currentTile = null;
            }
            tiles.get(pos).highlight();
            moving = true;
            currentPiece = tiles.get(pos).getPiece();
            currentTile = tiles.get(pos);
        } else {
            System.out.println("No piece on " + pos);
            if (currentTile != null) {
                currentTile.unhighlight();
            }
        }
    }
}
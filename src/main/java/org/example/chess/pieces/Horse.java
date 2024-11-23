package org.example.chess.pieces;

import org.example.chess.ChessBoard;
import org.example.chess.pieces.ChessPiece;

public class Horse extends ChessPiece {
    public Horse(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    @Override
    public boolean canMove(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return (Math.abs(toLine - line) == 1 && Math.abs(toColumn - column) == 2) ||
                (Math.abs(toLine - line) == 2 && Math.abs(toColumn - column) == 1);
    }

    @Override
    protected boolean isAProperPiece(ChessBoard board, int line, int column) {
        return board.board[line][column] instanceof Horse;
    }

    @Override
    protected boolean hasFigureInAWay(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return board.board[toLine][toColumn] != null
                && board.board[toLine][toColumn].color.equals(this.color);
    }
}
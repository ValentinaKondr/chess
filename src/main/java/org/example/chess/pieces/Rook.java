package org.example.chess.pieces;

import org.example.chess.ChessBoard;
import org.example.chess.pieces.ChessPiece;

import java.util.stream.IntStream;

public class Rook extends ChessPiece {
    public Rook(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    @Override
    public boolean canMove(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return ((toLine - line == 0) && (toColumn - column != 0)) ||
                ((toColumn - column == 0) && (toLine - line != 0));
    }

    @Override
    protected boolean isAProperPiece(ChessBoard board, int line, int column) {
        return board.board[line][column] instanceof Rook;
    }

    @Override
    protected boolean hasFigureInAWay(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (toLine - line == 0) {
            int[] columnIndexes = IntStream.range(column + 1, toColumn).toArray();
            for (int columnIndex : columnIndexes) {
                if (board.board[line][columnIndex] != null) {
                    return true;
                }
            }
        }
        if (toColumn - column == 0) {
            int[] lineIndexes = IntStream.range(line + 1, toLine).toArray();
            for (int lineIndex : lineIndexes) {
                if (board.board[lineIndex][column] != null) {
                    return true;
                }
            }
        }

        return board.board[toLine][toColumn] != null
                && board.board[toLine][toColumn].color.equals(board.currentPlayerColor());
    }
}
package org.example.chess.pieces;

import org.example.chess.ChessBoard;
import org.example.chess.Utils;

import java.util.stream.IntStream;

public class Bishop extends ChessPiece {
    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    @Override
    public boolean canMove(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return Math.abs(toLine - line) == Math.abs(toColumn - column);
    }

    @Override
    protected boolean isAProperPiece(ChessBoard board, int line, int column) {
        return board.board[line][column] instanceof Bishop;
    }

    @Override
    protected boolean hasFigureInAWay(ChessBoard board, int line, int column, int toLine, int toColumn) {
        int[] lineIndexes;
        int[] columnIndexes;
        if (toLine - line > 0) {
            lineIndexes = IntStream.range(line + 1, toLine).toArray();
        } else {
            lineIndexes = IntStream.range(toLine + 1, line).toArray();
            Utils.reverseArray(lineIndexes);
        }

        if (toColumn - column > 0) {
            columnIndexes = IntStream.range(column + 1, toColumn).toArray();
        } else {
            columnIndexes = IntStream.range(toColumn + 1, column).toArray();
            Utils.reverseArray((columnIndexes));
        }

        for (int index = 0; index < lineIndexes.length; index++) {
            if (board.board[lineIndexes[index]][columnIndexes[index]] != null) {
                return true;
            }
        }

        return board.board[toLine][toColumn] != null
                && board.board[toLine][toColumn].color.equals(board.currentPlayerColor());
    }
}
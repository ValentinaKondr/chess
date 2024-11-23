package org.example.chess.pieces;

import org.example.chess.ChessBoard;
import org.example.chess.Utils;

import java.util.stream.IntStream;

public class Queen extends ChessPiece {
    public Queen(String colour) {
        super(colour);
    }

    @Override
    public String getSymbol() {
        return "Q";
    }

    @Override
    public boolean canMove(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return canMoveLikeRook(line, column, toLine, toColumn)
                || canMoveLikeBishop(line, column, toLine, toColumn);
    }

    @Override
    protected boolean isAProperPiece(ChessBoard board, int line, int column) {
        return board.board[line][column] instanceof Queen;
    }

    @Override
    protected boolean hasFigureInAWay(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (hasFigureInARookMovementWay(board, line, column, toLine, toColumn)) return true;
        if (hasFigureInABishopMovementWay(board, line, column, toLine, toColumn)) return true;

        return board.board[toLine][toColumn] != null
                && board.board[toLine][toColumn].color == board.board[line][column].color;

    }

    private boolean canMoveLikeRook(int line, int column, int toLine, int toColumn) {
        return ((toLine - line == 0) && (toColumn - column != 0)) ||
                ((toColumn - column == 0) && (toLine - line != 0));
    }

    private boolean canMoveLikeBishop(int line, int column, int toLine, int toColumn) {
        return Math.abs(toLine - line) == Math.abs(toColumn - column);
    }

    private boolean hasFigureInABishopMovementWay(ChessBoard board, int line, int column, int toLine, int toColumn) {
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
            Utils.reverseArray(columnIndexes);
        }

        for (int index = 0; index < lineIndexes.length; index++) {
            if (board.board[lineIndexes[index]][columnIndexes[index]] != null) {
                return true;
            }
        }
        return false;
    }

    protected boolean hasFigureInARookMovementWay(ChessBoard board, int line, int column, int toLine, int toColumn) {
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
        return false;
    }
}
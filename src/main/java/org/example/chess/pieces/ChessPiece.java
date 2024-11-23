package org.example.chess.pieces;

import org.example.chess.ChessBoard;

public abstract class ChessPiece {
    public boolean checkFirstMove = true;
    protected String color;
    protected Boolean check;

    protected ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public boolean canMoveToPosition(
            ChessBoard board,
            int line, int column,
            int toLine, int toColumn
    ) {
        return isAProperPiece(board, line, column)
                && insideBoard(toLine, toColumn)
                && !isInitialPosition(line, column, toLine, toColumn)
                && canMove(board, line, column, toLine, toColumn)
                && !hasFigureInAWay(board, line, column, toLine, toColumn);
    }

    public abstract boolean canMove(
            ChessBoard board,
            int line, int column,
            int toLine, int toColumn
    );

    public abstract String getSymbol();

    protected boolean isInitialPosition(int line, int column, int toLine, int toColumn) {
        return line == toLine && column == toColumn;
    }

    protected abstract boolean isAProperPiece(ChessBoard board, int line, int column);

    protected boolean insideBoard(int line, int column) {
        return line >= 0 && line <= 7 && column >= 0 && column <= 7;
    }

    protected abstract boolean hasFigureInAWay(ChessBoard board, int line, int column, int toLine, int toColumn);

}
package org.example.chess.pieces;

import org.example.chess.ChessBoard;

import java.util.List;

public class Pawn extends ChessPiece {
    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean canMove(ChessBoard board, int line, int column, int toLine, int toColumn) {
        return canMoveLikePawn(line, column, toLine, toColumn);
    }

    @Override
    protected boolean isAProperPiece(ChessBoard board, int line, int column) {
        return board.board[line][column] instanceof Pawn;
    }

    private boolean canMoveLikePawn(int line, int column, int toLine, int toColumn) {
        // can either move forward 1 or 2 spaces or attack diagonally
        return isFirstMove(line, column, toLine, toColumn)
                || isAttackOrMove(line, column, toLine, toColumn);

    }

    @Override
    protected boolean hasFigureInAWay(ChessBoard board, int line, int column, int toLine, int toColumn) {
        if (toColumn - column == 0) {
            if (toLine - line == 1) {
                return board.board[toLine][toColumn] != null;
            }
            if (toLine - line == 2) {
                return (board.board[toLine][toColumn] != null || board.board[toLine - 1][toColumn] != null);
            }
        }


        return board.board[toLine][toColumn] != null
                && board.board[toLine][toColumn].color.equals(this.color);
    }

    private boolean isFirstMove(int line, int column, int toLine, int toColumn) {
        return (line == 1 && (List.of(2, 3).contains(toLine)) && column == toColumn)
                || (line == 6 && (List.of(4, 5).contains(toLine)) && column == toColumn);
    }

    private boolean isAttackOrMove(int line, int column, int toLine, int toColumn) {
        boolean isMoveValid = false;
        if (this.color.equals("White")) {
            isMoveValid = (toLine - line == 1);
        } else if (this.color.equals("Black")) {
            isMoveValid = (line - toLine == 1);
        } else {
            System.out.println("Internal error!");
            return false;
        }

        return isMoveValid && List.of(0, 1).contains(toColumn - column);
    }
}
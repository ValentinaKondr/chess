package org.example;

import org.example.chess.ChessBoard;
import org.example.chess.pieces.*;

import java.util.Scanner;
import java.util.function.Consumer;

public class Main {
    public static ChessBoard buildBoard() {
        ChessBoard board = new ChessBoard("White");

        Consumer<String> placeFigures = color -> {
            int line;
            int pawnLine;
            switch (color) {
                case "White" -> { line = 0; pawnLine = 1; }
                case "Black" -> { line = 7; pawnLine = 6; }
                default -> { return; }
            };

            board.board[line][0] = new Rook(color);
            board.board[line][1] = new Horse(color);
            board.board[line][2] = new Bishop(color);
            board.board[line][3] = new Queen(color);
            board.board[line][4] = new King(color);
            board.board[line][5] = new Bishop(color);
            board.board[line][6] = new Horse(color);
            board.board[line][7] = new Rook(color);

            for (int i = 0; i < board.board.length; i++) {
                board.board[pawnLine][i] = new Pawn(color);
            }
        };

        placeFigures.accept("White");
        placeFigures.accept("Black");
        return board;
    }

    public static void main(String[] args) {

        ChessBoard board = buildBoard();
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
               Чтобы проверить игру надо вводить команды:
               'exit' - для выхода
               'replay' - для перезапуска игры
               'castling0' или 'castling7' - для рокировки по соответствующей линии
               'move 1 1 2 3' - для передвижения фигуры с позиции 1 1 на 2 3(поле это двумерный массив от 0 до 7)
               Проверьте могут ли фигуры ходить друг сквозь друга, корректно ли съедают друг друга, можно ли поставить шах и сделать рокировку?""");
        System.out.println();
        board.printBoard();
        while (true) {
            String s = scanner.nextLine();
            if (s.equals("exit")) break;
            else if (s.equals("replay")) {
                System.out.println("Заново");
                board = buildBoard();
                board.printBoard();
            } else {
                if (s.equals("castling0")) {
                    if (board.castling0()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.equals("castling7")) {
                    if (board.castling7()) {
                        System.out.println("Рокировка удалась");
                        board.printBoard();
                    } else {
                        System.out.println("Рокировка не удалась");
                    }
                } else if (s.contains("move")) {
                    String[] a = s.split(" ");
                    try {
                        int line = Integer.parseInt(a[1]);
                        int column = Integer.parseInt(a[2]);
                        int toLine = Integer.parseInt(a[3]);
                        int toColumn = Integer.parseInt(a[4]);
                        if (board.moveToPosition(line, column, toLine, toColumn)) {
                            System.out.println("Успешно передвинулись");
                            board.printBoard();
                        } else System.out.println("Передвижение не удалось");
                    } catch (Exception e) {
                        System.out.println("Вы что-то ввели не так, попробуйте ещё раз");
                    }
                }
            }
        }
    }
}
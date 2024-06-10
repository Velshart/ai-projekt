import enums.ChessBoardTile;

import java.util.Scanner;

public class Main {
    static final int SIZE = 8;


    public static void main(String[] args) {
        boolean[][] board = new boolean[SIZE][SIZE];

        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj pozycję pierwszego hetmana, na przykład A8");
        ChessBoardTile firstQueenTile;

        while(true) {
            try {
                firstQueenTile = ChessBoardTile.valueOf(scanner.nextLine().toUpperCase());

                //ustawiamy pierwszego hetmana na danej pozycji
                board[firstQueenTile.getRow()][firstQueenTile.getColumn()] = true;

                break;

            } catch (IllegalArgumentException ex) {
                System.out.println("Podano nieprawidłową pozycję, spróbuj ponownie.");
            }
        }

        boolean solutionFound = placeQueensE(SIZE, board, 0, firstQueenTile.getRow());

        if(solutionFound) {
            printSolution(firstQueenTile, board);

        }else {
            System.out.println("Nie znaleziono rozwiązania.");
        }
    }


    /**
     * Metoda znajdująca rozwiązanie problemu n hetmanów.
     *
     * @param n długość boku szachownicy
     * @param board tablica wartości logicznych reprezentująca szachownicę
     * @param currentRow poziom rekurencji (aktualnie sprawdzany wiersz)
     * @param firstQueenRow wiersz, w którym umieszczono pierwszego hetmana.
     * @return true, jeśli znaleziono rozwiązanie, false w przeciwnym wypadku.
     */
    private static boolean placeQueensE(int n, boolean[][] board, int currentRow, int firstQueenRow) {

        for (int i = 0; i < n; i++) {
            // Pomijamy wiersz, który ma być pominięty
            if (currentRow == firstQueenRow) {
                if (currentRow == n - 1) {
                    return true;
                }
                return placeQueensE(n, board, currentRow + 1, firstQueenRow);
            }

            // Jeśli pole (currentRow, i) nie jest szachowane...
            if (isSafeB(board, currentRow, i, n)) {
                board[currentRow][i] = true;

                if (currentRow == n - 1) {
                    // Udało się dojść do końca i poprawnie rozstawić hetmany.
                    return true;
                }
                if (placeQueensE(n, board, currentRow + 1, firstQueenRow)) {
                    return true;
                } else {
                    board[currentRow][i] = false;
                }
            }
        }
        // Jeśli po zakończeniu pętli nie udało się zwrócić true, oznacza to, że nie ma rozwiązania.
        return false;
    }

    /**
     * Sprawdza czy podane w argumencie pole jest w zasięgu ataku jakiegokolwiek hetmana na szachownicy.
     *
     * @param board tablica wartości logicznych reprezentująca szachownicę
     * @param row wiersz, w którym znajduje się pole do sprawdzenia
     * @param col kolumna, w której znajduje się pole do sprawdzenia
     * @param n długość boku szachownicy
     * @return true, jeśli podane pole nie jest szachowane, false w przeciwnym wypadku.
     */
    private static boolean isSafeB(boolean[][] board, int row, int col, int n) {
        //kolumna kolejno po polach w górę
        for (int i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        //lewa przekątna w górę
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) {
                return false;
            }
        }

        //prawa przekątna w górę
        for (int i = row, j = col; i >= 0 && j < n; i--, j++) {
            if (board[i][j]) {
                return false;
            }
        }

        //kolumna kolejno po polach w dół
        for (int i = row + 1; i < n; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        //lewa przekątna w dół
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j]) {
                return false;
            }
        }

        //prawa przekątna w dół
        for (int i = row, j = col; i < n && j < n; i++, j++) {
            if (board[i][j]) {
                return false;
            }
        }

        return true;
    }

    private static void printSolution(ChessBoardTile firstQueenTile ,boolean [][] board) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(i == firstQueenTile.getRow()) {
                    System.out.print(board[i][j] ? "h" : ".");
                }else {
                    System.out.print(board[i][j] ? "H" : ".");
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println("gdzie h oznacza pozycję pierwszego hetmana.");
        System.out.println("\n" + "--------------------ALTERNATYWNIE--------------------");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j]) {
                    System.out.print(ChessBoardTile.fromCoordinates(i, j) + " ");
                }
            }
            System.out.println();
        }
    }
}
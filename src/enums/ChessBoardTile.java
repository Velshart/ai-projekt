package enums;

public enum ChessBoardTile {
    A8(0, 0), B8(0, 1), C8(0, 2), D8(0, 3), E8(0, 4), F8(0, 5), G8(0, 6), H8(0, 7),
    A7(1, 0), B7(1, 1), C7(1, 2), D7(1, 3), E7(1, 4), F7(1, 5), G7(1, 6), H7(1, 7),
    A6(2, 0), B6(2, 1), C6(2, 2), D6(2, 3), E6(2, 4), F6(2, 5), G6(2, 6), H6(2, 7),
    A5(3, 0), B5(3, 1), C5(3, 2), D5(3, 3), E5(3, 4), F5(3, 5), G5(3, 6), H5(3, 7),
    A4(4, 0), B4(4, 1), C4(4, 2), D4(4, 3), E4(4, 4), F4(4, 5), G4(4, 6), H4(4, 7),
    A3(5, 0), B3(5, 1), C3(5, 2), D3(5, 3), E3(5, 4), F3(5, 5), G3(5, 6), H3(5, 7),
    A2(6, 0), B2(6, 1), C2(6, 2), D2(6, 3), E2(6, 4), F2(6, 5), G2(6, 6), H2(6, 7),
    A1(7, 0), B1(7, 1), C1(7, 2), D1(7, 3), E1(7, 4), F1(7, 5), G1(7, 6), H1(7, 7);

    private final int row;
    private final int column;

    ChessBoardTile(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public static ChessBoardTile fromCoordinates(int row, int column) {
        for (ChessBoardTile position : values()) {
            if (position.row == row && position.column == column) {
                return position;
            }
        }
        throw new IllegalArgumentException("Invalid coordinates: " + row + ", " + column);
    }

    @Override
    public String toString() {
        return name() + " (" + row + ", " + column + ")";
    }
}

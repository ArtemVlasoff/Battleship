package battleship;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class GameFlow {

    public static final int WIN_CONDITION = 17;
    private GameField field1, field2, fog1, fog2;

    public GameFlow(GameField field1, GameField field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        GameField fog1 = new GameField("Player 1");
        GameField fog2 = new GameField("Player 2");
        int fragCounter1 = 0;
        int fragCounter2 = 0;
        int turnCounter = 0;

        while (true) {
            if (turnCounter % 2 == 0) {
                fog2.printField();
                System.out.println("---------------------");
                field1.printField();
                System.out.println(field1.player = ", it's your turn:");
                switch (fog2.shootCell(new Shot(scanner.nextLine().split("\\s")), field2)) {
                    case 0:
                        System.out.println("\nYou missed!\n" +
                                "Press Enter and pass the move to another player");
                        turnCounter++;
                        scanner.nextLine();
                        break;
                    case 1:
                        System.out.println("\nYou hit a ship!\n" +
                                "Press Enter and pass the move to another player");
                        fragCounter1++;

                        if (fragCounter1 == WIN_CONDITION) {
                            System.out.println(field1.player + ", you sank the last ship. You won. Congratulations!");
                            break;
                        }

                        turnCounter++;
                        scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("\nYou hit a ship!\n" +
                                "Press Enter and pass the move to another player");
                        turnCounter++;
                        scanner.nextLine();
                        break;

                    case 3:
                        System.out.println("\nYou sank a ship!\n" +
                                "Press Enter and pass the move to another player");
                        fragCounter1++;

                        if (fragCounter1 == WIN_CONDITION) {
                            System.out.println(field1.player + ", you sank the last ship. You won. Congratulations!");
                            break;
                        }

                        turnCounter++;
                        scanner.nextLine();
                        break;
                }
            } else {
                fog1.printField();
                System.out.println("---------------------");
                field2.printField();
                System.out.println(field2.player = ", it's your turn:");
                switch (fog1.shootCell(new Shot(scanner.nextLine().split("\\s")), field1)) {
                    case 0:
                        System.out.println("\nYou missed!\n" +
                                "Press Enter and pass the move to another player");
                        turnCounter++;
                        scanner.nextLine();
                        break;
                    case 1:
                        System.out.println("\nYou hit a ship!\n" +
                                "Press Enter and pass the move to another player");
                        fragCounter2++;

                        if (fragCounter2 == WIN_CONDITION) {
                            System.out.println(field2.player + ", you sank the last ship. You won. Congratulations!");
                            break;
                        }

                        turnCounter++;
                        scanner.nextLine();
                        break;
                    case 2:
                        System.out.println("\nYou hit a ship!\n" +
                                "Press Enter and pass the move to another player");
                        turnCounter++;
                        scanner.nextLine();
                        break;
                    case 3:
                        System.out.println("\nYou sank a ship!\n" +
                                "Press Enter and pass the move to another player");
                        fragCounter2++;

                        if (fragCounter2 == WIN_CONDITION) {
                            System.out.println(field2.player + ", you sank the last ship. You won. Congratulations!");
                            break;
                        }

                        turnCounter++;
                        scanner.nextLine();
                        break;
                }
            }

            System.out.println("The game score is " + fragCounter1 + ":" + fragCounter2);

            if (fragCounter1 == WIN_CONDITION) {
                System.out.println(field1.player + ", you sank the last ship. You won. Congratulations!");
                break;
            }

            if (fragCounter2 == WIN_CONDITION) {
                System.out.println(field2.player + ", you sank the last ship. You won. Congratulations!");
                break;
            }
        }

    }
}

class Shot {

    public int col, row;

    public Shot(String[] input) {
        this.col = input[0].charAt(0) - 'A' + 1;
        this.row = Integer.parseInt(input[0].substring(1));
    }

    public boolean isInRange() {
        if (col > 10 || col < 1 || row > 10 || row < 1) {
            return false;
        }

        return true;
    }

}

class Coordinate {

    public int beginCol, beginRow, endCol, endRow;

    public Coordinate(String[] input) {
        this.beginCol = Math.min(input[0].charAt(0) - 'A' + 1, input[1].charAt(0) - 'A' + 1);
        this.beginRow = Math.min(Integer.parseInt(input[0].substring(1)),
                Integer.parseInt(input[1].substring(1)));
        this.endCol = Math.max(input[0].charAt(0) - 'A' + 1, input[1].charAt(0) - 'A' + 1);
        this.endRow = Math.max(Integer.parseInt(input[0].substring(1)),
                Integer.parseInt(input[1].substring(1)));
    }
}

class Cell {
    int col;
    int row;

    Cell(int col, int row) {
        this.col = col;
        this.row = row;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Cell)) {
            return false;
        }

        Cell cell = (Cell) obj;
        return (col == cell.col) && (row == cell.row);

    }
}

class FieldCreator {

    final String LETTERS = "ABCDEFGHIJK";
    public static final String[] SHIPS = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser",
            "Destroyer"};
    public static final int[] SHIP_LENGTH = {5, 4, 3, 3, 2};

    public GameField createField(String name) {
        return new GameField(name);
    }

    public void placeShips(GameField field) {

        Scanner scanner = new Scanner(System.in);
        int i = 0;

        System.out.println("\n" + field.player + ", place your ships on the game field\n");
        field.printField();

        while (i < 5) {
            int len = SHIP_LENGTH[i];
            String name = SHIPS[i];

            System.out.println("\nEnter the coordinates of the " + name +
                    " (" + len + " cells):\n");
            Coordinate coor =  new Coordinate(scanner.nextLine().split("\\s"));

            if (!isInRange(coor) || !isFloat(coor) ||
                    !isEmpty(coor, field)) {
                System.out.println("\nError! Wrong ship location! Try again:\n");
                continue;
            }

            if (!isNeededLength(coor, len)) {
                System.out.println("\nError! Wrong length of the " + name + " ! Try again:\n");
                continue;
            }

            if (!isTooNear(coor, field)) {
                System.out.println("\nError! You placed it too close to another one. Try again:\n");
                continue;
            }

            field.placeShip(coor, i);
            i++;
        }

        System.out.println("\nPress Enter and pass the move to another player\n");
        scanner.nextLine();

    }

    public boolean isInRange(Coordinate coor) {
        if (coor.beginCol > 10 || coor.beginCol < 1|| coor.beginRow > 10||
                coor.beginRow < 1 || coor.endCol > 10 || coor.endCol < 0 ||
                coor.endRow > 10 || coor.endRow > 10) {
            return false;
        }

        return true;
    }

    public boolean isFloat(Coordinate coor) {
        if (coor.beginCol == coor.endCol || coor.beginRow == coor.endRow) {
            return true;
        }

        return false;
    }

    public boolean isNeededLength(Coordinate coor, int len) {
        if (Math.abs(coor.beginCol - coor.endCol) == len - 1 ||
                Math.abs(coor.beginRow - coor.endRow) == len - 1) {
            return true;
        }

        return false;
    }

    public boolean isEmpty(Coordinate coor, GameField field) {
        for (int i = coor.beginCol; i <= coor.endCol; i++) {
            for (int k = coor.beginRow; k <= coor.endRow; k++) {
                if (!field.isAvailable(i, k)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isTooNear(Coordinate coor, GameField field) {
        int beginX = coor.beginRow < 0 ? 1 : Math.min(coor.beginRow , 10);
        int beginY = coor.beginCol < 0 ? 1 : Math.min(coor.beginCol , 10);
        int endX = coor.endRow < 0 ? 1 : Math.min(coor.endRow, 10);
        int endY = coor.endCol < 0 ? 1 : Math.min(coor.endCol, 10);

        for (int i = beginY; i < endY; i++) {
            for (int k = beginX; k < endX; k++) {
                System.out.println("Checking cell " + i + ", " + k);
                if (!field.isAvailable(i, k)) {
                    return false;
                }
            }
        }

        return true;
    }

}

class Ship {
    List<Cell> coords = new ArrayList<>();
}

class GameField {

    public String player;
    final String LETTERS = "ABCDEFGHIJK";
    public String[][] field;
    private String[][] fieldAvailability;
    public Ship[] ships = new Ship[5];

    public GameField(String name) {
        field = new String[12][11];
        this.player = name;

        for (int i = 0; i < field.length; i++) {
            for (int k = 0; k < field[i].length; k++) {
                if (i == 0 && k == 0) {
                    field[i][k] = " ";
                } else if (i == 0) {
                    field[i][k] = String.valueOf(k);
                } else if (k == 0) {
                    field[i][k] = LETTERS.substring(i - 1, i);
                } else {
                    field[i][k] = "~";
                }
            }
        }

        fieldAvailability = new String[12][11];

        for (int i = 0; i < fieldAvailability.length; i++) {
            for (int k = 0; k < fieldAvailability[i].length; k++) {
                fieldAvailability[i][k] = "v";
            }
        }

        for (int i = 0; i < ships.length; i++) {
            ships[i] = new Ship();
        }
    }

    public boolean isAvailable(int col, int row) {
        if (fieldAvailability[col][row] == "v") {
            return true;
        }

        return false;
    }

    public void printField() {
        for (int i = 0; i < field.length - 1; i++) {
            for (int k = 0; k < field[i].length; k++) {
                System.out.print(field[i][k] + " ");
            }
            System.out.println();
        }
    }

    public void placeShip(Coordinate coor, int name) {
        Ship ship = ships[name];

        //Filling map of availability
        for (int i = coor.beginCol - 1; i <= coor.endCol + 1; i++) {
            if (i < 1) {
                i++;
            }

            if (i > 10) {
                continue;
            }
            for (int k = coor.beginRow - 1; k <= coor.endRow + 1; k++) {
                if (k < 1) {
                    k++;
                }

                if (k > 10) {
                    continue;
                }
                fieldAvailability[i][k] = "x";
            }
        }

        //Filling ship cells
        for (int i = coor.beginCol; i <= coor.endCol; i++) {
            for (int k = coor.beginRow; k <= coor.endRow; k++) {
                field[i][k] = "O";
                ship.coords.add(new Cell(i, k));
            }
        }

        printField();
    }

    public boolean checkShip(int col, int row) {
        Cell cell = new Cell(col, row);
        Ship checkedShip = null;

        for (Ship ship : ships) {
            for (Cell coord : ship.coords) {
                if (coord.equals(cell)) {
                    checkedShip = ship;
                }
            }
        }

        if (checkedShip == null) {
            return false;
        }

        for (Cell checkedCoord : checkedShip.coords) {
            if ("O".equals(field[checkedCoord.col][checkedCoord.row])) {
                return false;
            }
        }

        return true;
    }

    public int shootCell(Shot shot, GameField fieldToCompare) {

        if (shot.isInRange()) {
            switch (fieldToCompare.field[shot.col][shot.row]) {
                case "~":
                    field[shot.col][shot.row] = "M";
                    fieldToCompare.field[shot.col][shot.row] = "M";
                    return 0;
                case "O":
                    field[shot.col][shot.row] = "X";
                    fieldToCompare.field[shot.col][shot.row] = "X";
                    System.out.println("Checking ship...");
                    if (checkShip(shot.col, shot.row)) {
                        System.out.println("The ship is sank");
                        return 3;
                    };

                    System.out.println("the ship is not sank");
                    return 1;
                case "X":
                    return 2;
            }
        } else {
            System.out.println("\nWrong coordinates! Try again:\n");
        }

        return 0;
    }
}

public class Main {

    public static void main(String[] args) {

        FieldCreator fc = new FieldCreator();
        GameField field1 = fc.createField("Player 1");
        GameField field2 = fc.createField("Player 2");
        fc.placeShips(field1);
        fc.placeShips(field2);
        GameFlow game = new GameFlow(field1, field2);
        game.startGame();

    }
}

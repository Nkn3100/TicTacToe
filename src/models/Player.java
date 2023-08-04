package models;

import java.util.Scanner;

public class Player {
    private static int idCounter = 0;
    private Symbol symbol;
    private String name;
    private int id;
    private PlayerType playerType;
    private Scanner scanner;

    public Player(Symbol symbol, String name, PlayerType playerType) {
        this.symbol = symbol;
        this.name = name;
        this.id = idCounter++;
        this.playerType = playerType;
        this.scanner = new Scanner(System.in);
    }
    public Move makeMove(Board board){
        System.out.println(this.name +": Please enter the row for the move");
        int row = scanner.nextInt();
        System.out.println(this.name +": Please enter the column for the move");
        int column = scanner.nextInt();

        //validate the move and throw exception or msg
        //TODO
        board.getBoard().get(row).get(column).setPlayer(this);
        board.getBoard().get(row).get(column).setCellState(CellState.FILLED);
        return new Move(new Cell(row, column,this), this);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}

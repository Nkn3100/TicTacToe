package strategies.winningstrategies;

import exception.GameDrwanException;
import models.Board;
import models.Move;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneWinningStrategy implements WinningStrategy{
    public  int dimension;
    private int symbolsAdded;
    private List<HashMap<Character,Integer>> rowSymbolCount = new ArrayList<>();
    private List<HashMap<Character,Integer>> colSymbolCount = new ArrayList<>();
    private HashMap<Character,Integer> topLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character,Integer> bottomLeftDiagonalSymbolCount = new HashMap<>();
    private HashMap<Character,Integer> cornerSymbolCount = new HashMap<>();

    public boolean isCellTopLeftDiagonal(int row, int col){
        return row == col;
    }
    public boolean isCellBottomLeftDiagonal(int row, int col){
        return (row+col) == dimension -1;
    }
    public boolean isCornerCell(int row, int col){
        if(row == 0 || row == dimension -1){
            return col == 0 || col == dimension - 1 ;
        }
        return false;
    }
    public OrderOneWinningStrategy(int dimension) {
        this.symbolsAdded = 0;
        this.dimension = dimension;
        for(int i= 0; i < dimension; i++){
            rowSymbolCount.add(new HashMap<>());
            colSymbolCount.add(new HashMap<>());
        }
    }

    @Override
    public Player checkWinner(Board board, Move lastMove) {
        symbolsAdded++;
        Player lastMovePlayer = lastMove.getPlayer();
        char symbol = lastMove.getPlayer().getSymbol().getSymbolChar();
        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getColumn();

        if(checkForColumns(row, col, symbol, lastMove) != null){
            return lastMovePlayer;
        }else if(checkForRows(row, col, symbol, lastMove) != null){
            return lastMovePlayer;
        }else if(checkForDiagonalWins(row, col, symbol, lastMove) != null){
            return lastMovePlayer;
        }else if(checkForCorners(row, col, symbol, lastMove) != null){
            return lastMovePlayer;
        }
        // checking draw condition
        if(symbolsAdded == (dimension*dimension)){
            board.printBoard();
            throw new GameDrwanException("Game is drawn as cells are full");
        }
        return null;


    }
    private Player checkForDiagonalWins(int row, int col, char symbol, Move lastMove){
        if(isCellTopLeftDiagonal(row,col)){
            if(!topLeftDiagonalSymbolCount.containsKey(symbol)){
                topLeftDiagonalSymbolCount.put(symbol,0);
            }
            topLeftDiagonalSymbolCount.put(
                    symbol,
                    topLeftDiagonalSymbolCount.get(symbol) + 1
            );
            //winning by same symbol across a column
            if(topLeftDiagonalSymbolCount.get(symbol) == dimension){
                return lastMove.getPlayer();
            }
        }
        if(isCellBottomLeftDiagonal(row,col)){
            if(!bottomLeftDiagonalSymbolCount.containsKey(symbol)){
                bottomLeftDiagonalSymbolCount.put(symbol,0);
            }
            bottomLeftDiagonalSymbolCount.put(
                    symbol,
                    bottomLeftDiagonalSymbolCount.get(symbol) + 1
            );
            //winning by same symbol across a column
            if(bottomLeftDiagonalSymbolCount.get(symbol) == dimension){
                return lastMove.getPlayer();
            }
        }
        return null;
    }
    private Player checkForCorners(int row, int col, char symbol, Move lastMove){
        if(isCornerCell(row, col)){
            if(!cornerSymbolCount.containsKey(symbol)){
                cornerSymbolCount.put(symbol,0);
            }
            cornerSymbolCount.put(
                    symbol,
                    cornerSymbolCount.get(symbol) + 1
            );
            //winning by same symbol across a column
            if(cornerSymbolCount.get(symbol) == dimension){
                return lastMove.getPlayer();
            }
        }
        return null;
    }
    private Player checkForRows(int row, int col, char symbol, Move lastMove){
        if(!rowSymbolCount.get(row).containsKey(symbol)){
            rowSymbolCount.get(row).put(symbol,0);
        }
        rowSymbolCount.get(row).put(
                symbol,
                rowSymbolCount.get(row).get(symbol) + 1
        );
        //winning by same symbol across a row
        if(rowSymbolCount.get(row).get(symbol) == dimension){
            return lastMove.getPlayer();
        }
        return null;
    }
    private Player checkForColumns(int row, int col, char symbol, Move lastMove){
        if(!colSymbolCount.get(col).containsKey(symbol)){
            colSymbolCount.get(col).put(symbol,0);
        }
        colSymbolCount.get(col).put(
                symbol,
                colSymbolCount.get(col).get(symbol) + 1
        );
        //winning by same symbol across a column
        if(colSymbolCount.get(col).get(symbol) == dimension){
            return lastMove.getPlayer();
        }
        return null;
    }

    //TODO:
    /*
          Implement 4 classes for Winning Strategy
          1. RowWinningStrategy
          2.ColumnWinningStrategy
          3.DiagonalWinningStrategy
          4.CornerWinningStrategy

     */
}

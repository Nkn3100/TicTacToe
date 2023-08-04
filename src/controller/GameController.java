package controller;

import models.Game;
import models.GameState;
import models.Move;
import models.Player;
import strategies.winningstrategies.OrderOneWinningStrategy;
import strategies.winningstrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game createGame(int dimension, List<Player> players){
        try{
            return Game.builder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .setWinningStrategies(List.of(new OrderOneWinningStrategy(dimension)))
                    .build();
        }catch(Exception e){
            System.out.println("Could not start the game. Something went wrong!");
        }
        return null;
    }
    public void displayBoard(Game game){
        game.getBoard().printBoard();
    }
    public GameState getGameState(Game game){
        return game.getGameState();
    }
    public Move executeMove(Game game, Player player){

        Move move = player.makeMove(game.getBoard());
        updateGameMove(game, move);
        return move;

    }
    private void updateGameMove(Game game, Move move){
        game.getMoves().add(move);
    }
    public String getWinner(Game game){
        return game.getWinner().getName();
    }
    public Player checkWinner(Game game, Move recentMove){
        for(WinningStrategy winningStrategy : game.getWinningStrategies()){
            Player player = winningStrategy.checkWinner(game.getBoard(), recentMove);
            if(player != null){
                return player;
            }
        }
        return null;
    }
}

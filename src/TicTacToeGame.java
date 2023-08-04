import controller.GameController;
import exception.DuplicateSymbolException;
import models.*;
import strategies.botplayingstrategies.BotPlayingStrategyFactory;

import java.util.*;

public class TicTacToeGame {
    public static void main(String[] args) {
        HashSet<String> symbolSet = new HashSet<>();
        Scanner scn = new Scanner(System.in);
        GameController gameController = new GameController();
        System.out.println("Please enter the dimension of the game");
        int dimension = scn.nextInt();
        System.out.println("Will there be any bot in the game ? Y/N");
        String isBotPresent = scn.next();
        List<Player> players = new ArrayList<>();
        int iteratorNumber = dimension - 1;
        if(isBotPresent.equals("Y")){
            iteratorNumber = dimension - 2;
        }
        for(int i = 0; i < iteratorNumber; i++){

            System.out.println("What is the name of the player : " + (i + 1));
            String playerName = scn.next();
            System.out.println("What is the character symbol of the player number : " + (i+1));
            String characterSymbol = scn.next();
            //validate if no one passes a duplicate symbol
            if(symbolSet.contains(characterSymbol)){
                throw new DuplicateSymbolException("Duplicate symbol is not allowed");
            }
            symbolSet.add(characterSymbol);
            players.add(new Player(new Symbol(characterSymbol.charAt(0)), playerName, PlayerType.HUMAN));
        }
        if(isBotPresent.equals("Y")){
            System.out.println("What is the name of the BOT");
            String botName = scn.next();
            System.out.println("What is the character symbol of the BOT");
            String characterSymbol = scn.next();
            if(symbolSet.contains(characterSymbol)){
                throw new DuplicateSymbolException("Duplicate symbol is not allowed");
            }
            symbolSet.add(characterSymbol);

            //create bot object and add in players list.
            BotDifficultyLevel difficultyLevel = BotDifficultyLevel.EASY;
            Bot bot = new Bot(new Symbol(characterSymbol.charAt(0)),
                    botName,
                    difficultyLevel,
                    BotPlayingStrategyFactory.getBotPlayingStrategyForDifficultyLevel(BotDifficultyLevel.EASY));
            players.add(bot);
        }
        //Randomizes the players in the list
        Collections.shuffle(players);

        Game game = gameController.createGame(dimension,players);
        int playerIndex = -1;
        //TODO: optimise the while loop and handle exception gracefully
        while(gameController.getGameState(game).equals(GameState.IN_PROGRESS)){
            System.out.println("CURRENT BOARD STATUS");
            gameController.displayBoard(game);
            playerIndex++;
            playerIndex = playerIndex % players.size();
            Move movePlayed = gameController.executeMove(game, players.get(playerIndex));
            //TODO : undo
            Player winner = gameController.checkWinner(game, movePlayed);
            if(winner != null){
                gameController.displayBoard(game);
                System.out.println("Winner is : " + winner.getName());
                break;
            }
        }
//        if(gameController.getGameState(game).equals(GameState.DRAW)){
//            System.out.println("Game was a draw");
//        }
    }
}

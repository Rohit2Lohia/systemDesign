-- https://www.interviewbit.com/low-level-design-interview-questions/
Code Implementation:

Driver.java

package snakeLadder;
 
 
public class Driver {
 
    public static void main(String[] args)
    {
        SpecialEntity snake1 = new Snake(12, 28);
        SpecialEntity snake2 = new Snake(34, 78);
        SpecialEntity snake3 = new Snake(6, 69);
        SpecialEntity snake4 = new Snake(65, 84);
 
        SpecialEntity ladder1 = new Ladder(24, 56);
        SpecialEntity ladder2 = new Ladder(43, 83);
        SpecialEntity ladder3 = new Ladder(3, 31);
        SpecialEntity ladder4 = new Ladder(72, 91);
 
        Board board = new Board(10);
        board.addSpecialEntity(snake1);
        board.addSpecialEntity(snake2);
        board.addSpecialEntity(snake3);
        board.addSpecialEntity(snake4)
 
        board.addSpecialEntity(ladder1);
        board.addSpecialEntity(ladder2);
        board.addSpecialEntity(ladder3);
        board.addSpecialEntity(ladder4);
 
        Dice dice = new Dice(6);
 
        Game game = new Game(board, dice);
       
        Player player1 = new Player("p1");
        Player player2 = new Player("p2");
        Player player3 = new Player("p3");
 
        players = List<Player>(){player1, player2, player3};
        game.addPlayers(players);
 
        game.launch();
    }
 
}
Game.java

package snakeLadder;
 
import snakeLadder.GameStatus;
import snakeLadder.Player;
import snakeLadder.Dice;
import snakeLadder.Board;
 
import java.util.Queue;
import java.util.Scanner;
import java.util.LinkedList;
 
public class Game {
 
    Board board;
    Dice dice;
    Queue<Player> players;
    GameStatus staus;
 
    public Game(Board board, Dice dice)
    {
        this.board = board;
        this.dice = dice;
        this.players = new LinkedList<Player>();
        this.status = GameStatus.NOT_STARTED;
    }
 
    public void startGame()
    {
        this.status = GameStatus.RUNNING;
        board.printBoard();
 
        // Run until we have only 1 player left on the board
        while(players.size() > 1)
        {
            Player player = players.poll();
 
            makeMove(currPlayer);
 
            if(player.getPosition() == board.getTotalCells())
                System.out.println(player.getName() + " has completed the game!");
            else
                players.add(player);
        }
 
        this.status = GameStatus.FINISHED;
 
    }
 
    private void makeMove(Player player) {
 
        System.out.println();
        System.out.println(currPlayer.getUserName()+"'s turn.");
        System.out.println("Press anything to roll the dice.");
 
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
 
        int playerPosition = player.getPosition();
        int rollValue = dice.roll();
 
        int targetPosition = playerPosition + rollValue;
 
        if(targetPosition > board.getTotalCells())
            System.out.println("Invalid Move");
        else
        {
            if(board.hasSpecialEntity(targetPosition))
            {
                targetPosition = board.getSpecialEntity(targetPosition).getEndPosition();
            }
        }
 
        player.setPosition(targetPosition);
 
    }
 
    public void addPlayers(List<Player> all_players)
    {
        if(this.status == GameStatus.NOT_STARTED)
        {
            for(Player player : all_players)
                this.players.add(player);
        }
        else
            throw new GameAlreadyStartedException("Can't add players after game started")
 
    } 
}
Board.java

package snakeLadder;
 
import lombok.Getter;
 
public class Board{
   
    @Getter
    int dimension;
 
    HashMap<Integer, SpecialEntity> specialEntities;
 
    public Board(int dimension)
    {
        this.dimension = dimension;
    }
 
    public void printBoard()
    {
        int totalCells = dimension*dimension;
        for(int i=totalCells; i > 0; i--)
        {
            System.out.print(" | " + i + " ")
           
            if(hasSpecialEntity(i))
                System.out.print(specialEntities.get(i).getID());
 
            System.out.print(" |");
            if(totalCells % 10 == 0)
                System.out.println();
        }
    }
 
    public int getTotalCells()
    {
        return this.dimesion * this.dimesion;
    }
 
    public void addSpecialEntity(SpecialEntity entity)
    {
        int actionPosition = entity.getActionPosition();
 
        specialEntities.put(actionPosition, entity);
    }
 
    public boolean hasSpecialEntity(int position)
    {
        return specialEntities.containsKey(position);
    }
 
    public SpecialEntity getSpecialEntity(int position)
    {
        if(hasSpecialEntity(position))
            return specialEntities.get(position);
 
        return null;
    }
}
Dice.java

package snakeLadder;
 
import lombok.Getter;
 
public class Dice{
   
    int maxValue;
 
    public Dice(int maxVal)
    {
        this.maxValue = maxVal;
    }
 
    public int roll()
    {
        return (int) Math.floor(Math.random()*maxValue + 1);
    }
}
GameStatus.java

package snakeLadder;
 
public enum GameStatus {
    NOT_STARTED,
    RUNNING,
    FINISHED
}
SpecialEntity.java

package snakeLadder;
 
import lombok.Getter;
public abstract class BoardEntity {
   
    private int start;
    private int end;
   
    public SpecialEntity(int start, int end) {
        this.start = start;
        this.end = end;
    }
 
    public abstract String getID();
 
    public int getActionPosition()
    {
        return this.start;
    }
 
    public int getEndPosition()
    {
        return this.end;
    }
}
Snake.java

package snakeLadder;
 
public class Snake extends SpecialEntity{
 
    public Snake(int start, int end) {
        super(start, end);
    }
 
    @Override
    public String getID() {
        return "S_"+ this.getEnd();
    } 
}
Ladder.java

package snakeLadder;
 
public class Ladder extends SpecialEntity{
 
    public Ladder(int start, int end) {
        super(start, end);
    }
 
    @Override
    public String getID() {
        return "L_"+ this.getEnd();
    } 
}
Player.java

package snakeLadder;
 
import lombok.Getter;
import lombok.Setter;
 
public class Player{
   
    @Getter
    @Setter
    int position;
 
    @Getter
    String name;
 
    public Player(String name)
    {
        this.name = name;
        this.position = 0;
    }
}
GameAlreadyStartedException.java

package snakeLadder;
 
public class GameAlreadyStartedException extends Exception {
    public GameAlreadyStartedException(String message) {
        super(message);
    }
}
It is important to structure the code into different classes with each class having a single responsibility 
(Remember S in SOLID Principles) and also having relations between the classes so that they can be easily extensible.
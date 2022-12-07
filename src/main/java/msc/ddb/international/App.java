package msc.ddb.international;

import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.MILLIS;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.Scanner;

/**
 * Hello world!
 * 
 */
public class App 
{
    static Scanner input = new Scanner(System.in);
    
    /** 
     * @param args
     */

     //Starten vom Logger JUL
    private static final Logger log = Logger.getLogger (App.class.getName());
    
    public static void main( String[] args )
    {

        // Maschinenzeit
        Instant start = now();

        // Datum+Zeit aus der lokalen Zeitzone
        LocalDateTime localDateTime = LocalDateTime.now();
        
        System.out.println("\n");

        log.info ("Starten Logging mit 'JUL' (Java Util Logging)");
        // create a game
        Game game = new Game();

        // instance a player
        Player player01 = new Player("Harald");
        game.setPlayer(player01);
        game.startGame();
        
        
        // lets play!
        System.out.println("--------------------\nWelcome to BlackJack!\n\n");
        int decision = 1;
        while(decision == 1) {
            try{
                System.out.println("Would you like to:\n1) Pick another Card?\n2) Chicken out?");
                decision = input.nextInt();
                switch (decision) {
                    case 1:
                        game.dealCard();
                        System.out.println(player01.getHand());
                        break;
                    case 2:
                        game.stopGame();
                        break;
                    default:
                        break;
                }

            }
            catch(Exception e){
                System.out.println("Invalid Input");
                input.next();
            }
        }
        // how did it run
        System.out.println(game);

        // Programmende:
        System.out.println("\n");
        log.info( () -> String.format( "Laufzeit %s ms", start.until( now(), MILLIS ) ) );
    }
}

package msc.ddb.international;


import msc.ddb.international.actors.Player;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.Scanner;
import java.time.Instant;
import java.time.LocalDateTime;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.MILLIS;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.UUID;
import java.net.InetAddress;

public class App 
{
    public static Scanner input = new Scanner(System.in); // Instantiation Input

    private static String actLang = "English"; // Variable = Camelcase
    private static final String FILENAME_ENVIRONMENT = "G3_Environment.txt"; // Filename = Konstante
    private static final String FILENAME_LOGGING = "G3_Logging.xml";
    private static final String FILENAME_GAME = "G3_Game.txt";

    private static final Logger log = Logger.getLogger(App.class.getName());
    public static FileHandler handlerLog; 
    public static Instant start = now();

    /** 
     * @param args
     */
    public static void main( String[] args )
    {
        
        startLogging();
        getEnvironment();
        consoleChooseLanguage()
            
        BlackJack game = new BlackJack();
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.initializeGame();
        game.startGame();
        
        finishApp();
    }

    public static void startLogging()
    {
        try {
            handlerLog = new FileHandler( FILENAME_LOGGING );
            log.addHandler(handlerLog);
        } catch (Exception e) {
            log.log(Level.WARNING, "Error while creating the logfile (" + FILENAME_LOGGING + ")", e);
        }
        log.info ("Start Logging with Java Util Logging (" + FILENAME_LOGGING + ")");
        
    }

    public static void writeEnvironmentFile(String contentParam) {
        
        try {
            Files.writeString( Path.of( FILENAME_ENVIRONMENT ), contentParam, StandardOpenOption.APPEND);
        } 
        catch (Exception e) {
            System.err.println("'%s' File (" + FILENAME_ENVIRONMENT + ") cannot be saved '%n'");
            e.printStackTrace();     
            log.severe("Error! - Error writing File (" + FILENAME_ENVIRONMENT + ")");
        }
    }

    public static void writeGameFile(String contentParam) {
        
        try {
            Files.writeString( Path.of( FILENAME_GAME ), contentParam, StandardOpenOption.APPEND);
        } 
        catch (Exception e) {
            System.err.println("'%s' File (" + FILENAME_GAME + ") cannot be saved '%n'");
            e.printStackTrace();     
            log.severe("Error! - Error writing File (" + FILENAME_GAME + ")");
        }
    }

    public static void getEnvironment()
    {
        System.out.println("Get environment\n");

        // Date + Time from local TimeZone
        LocalDateTime localDateTime = LocalDateTime.now();

        // UUID (Universal Unique Identifier)
        String content = "UUID      " + UUID.randomUUID() + "\n" + "Instant   " + start + "\n" + "LDateTime " + localDateTime + "\n";

        // If file exists, it will be deleted
        Path path = Paths.get( FILENAME_ENVIRONMENT );

        try {
            Files.deleteIfExists( path );
        } catch (IOException e) {
            e.printStackTrace();
            log.severe("Error! - Error deleting file (" + FILENAME_ENVIRONMENT + ")");
        }

        try {
            Files.writeString( Path.of( FILENAME_ENVIRONMENT ), content, StandardOpenOption.CREATE_NEW);
        } 
        catch (Exception e) {
            System.err.println("'%s' File cannot be saved '%n'");
            e.printStackTrace();     
            log.severe("Error! - Error deleting file - Logging with 'JUL' (Java Util Logging)");
        }
        finally {
            System.out.println("File (" + FILENAME_ENVIRONMENT + ") wurde geschrieben");
        }

        System.out.println("\n===>> Environment <<===");

        // InetAddress - Exception required!!!
        try {
            String Computer_Name = InetAddress.getLocalHost().getHostName();
            String IP_Address = InetAddress.getLocalHost().getHostAddress();
            
            content = "Hostname  " + Computer_Name + "\n";
            writeEnvironmentFile(content);
            content = "IPAddress " + IP_Address + "\n";
            writeEnvironmentFile(content);
        } catch (Exception e) {
            System.out.println("Error: UnknownHostException!");
            log.severe("Error! - Error writing file (" + FILENAME_ENVIRONMENT + ")");
        }

        String Java_Home = System.getProperty("java.home","/tmp/java");
        String OS = System.getProperty("os.name","Linux");
        String User_Name = System.getProperty("user.name","John Doe");
        String Java_Version = System.getProperty("java.version","19");

        content = "JavaHome  " + Java_Home + "\n";
        writeEnvironmentFile(content);
        content = "OSName    " + OS + "\n";
        writeEnvironmentFile(content);
        content = "UserName  " + User_Name + "\n";
        writeEnvironmentFile(content);
        content = "JavaVers  " + Java_Version + "\n";
        writeEnvironmentFile(content);
        content = "--------------------------------------------------------------------------------------------------------------------";
        writeEnvironmentFile(content);

        log.info ("Write Environments: " + User_Name);

    }

    public static void consoleChooseLanguage()
    {
        String[] validLang = {"German","English"};
        String actLang = "English"; // Defaultvalue
        boolean selectedLang = false;

        while (!selectedLang) {
            System.out.println("\nChoose a language: (German|English)\n");

            String inputLang = input.nextLine();

            for ( String s : validLang )
                if ( s.equals( inputLang ) ) {
                    selectedLang = true;
                    actLang = inputLang;
                    System.out.println("inputLang= " + actLang);
                    break;
                }            
        }
        log.info ("selected language: " + actLang);
        
    }
  
    public static void finishApp() {
        
        if(actLang.equals( "English" ))
            System.out.println("Card game finished." );
        else
            System.out.println("Translation (" + actLang + ") not implemented yet!" );

        System.out.println("\n");
        log.info( () -> String.format( "Runtime %s ms", start.until( now(), MILLIS))) ;
        handlerLog.close();        
    }
}

    }
}

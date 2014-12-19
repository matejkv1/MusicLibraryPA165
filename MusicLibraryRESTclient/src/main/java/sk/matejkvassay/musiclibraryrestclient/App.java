package sk.matejkvassay.musiclibraryrestclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App {
	private MusicianClient mc;
	private GenreClient genre;

	public static void main(String[] args) {
		App app = new App();
		app.run();
	}
	
	private void run() {
		mc = new MusicianClient();
		genre = new GenreClient();
		CmdLineReader.init();
		
		String input;
		
		for (;;) {
			input = CmdLineReader.readInput("Enter command:");
			Command com = new Command(input.toLowerCase());
			System.out.println(com.toString());

			if("exit".equals(com.getClsType())) {
				System.out.println("Exiting.");
				CmdLineReader.close();
				break;
			}
			
			switch(com.getClsType()) {
				case "musician":
					mc.processCommand(com.getCommand());
					break;
				case "genre": 
					genre.processCommand(com.getCommand());
					break;
				case "help":
					printHelp();
					break;
				default:
					System.out.println("Your command was not recognized.");
					printHelp();
					break;
			}
			
			System.out.println("");
		}
		
		System.exit(0);
	}
	
	

	private void printHelp() {
		System.out.println("asasdad");
	}
}

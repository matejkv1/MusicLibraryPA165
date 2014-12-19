package sk.matejkvassay.musiclibraryrestclient;

/**
 * Hello world!
 *
 */
public class App {
	public static final String BASE_URL = "http://localhost:8084/pa165/rest";
	
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

}
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
	
	public static void printHelp() {
		System.out.println("Available commands:");
		System.out.println("help - print help");
		System.out.println("exit - exit application");
		System.out.println("[type] [operation]");
		System.out.println("Possibilities: [musician|genre] [getall|getcount|get|insert|update|delete]");
		System.out.println("e.g. musician get");
	}

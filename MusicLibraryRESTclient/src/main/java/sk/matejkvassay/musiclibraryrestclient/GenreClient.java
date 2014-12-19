/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sk.matejkvassay.musiclibraryrestclient;

/**
 *
 * @author Horak
 */
public class GenreClient {
	public void processCommand(String command) {
		switch(command) {
			case "get":
				getAll();
				break;
			case "update":
				update();
				break;
			case "delete":
				delete();
				break;
			default:
				System.out.println("Action '" + command + "' not recognized.");
				App.printHelp();
				break;
		}
	}

	private void getAll() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	private void update() {
		System.out.println("Updating a genre.");
		String musicianID = CmdLineReader.readInput("ID of genre to update:");
		String name = CmdLineReader.readInput("New name of genre:");
		String description = CmdLineReader.readInput("New genre's description:");
		
		System.out.println("GENRE UPDATED SUCCESSFULLY");
	}
	
	private void delete() {
		System.out.println("Deleting a genre.");
		String musicianID = CmdLineReader.readInput("ID of genre to delete:");
		
		System.out.println("GENRE DELETED SUCCESSFULLY");
	}
}

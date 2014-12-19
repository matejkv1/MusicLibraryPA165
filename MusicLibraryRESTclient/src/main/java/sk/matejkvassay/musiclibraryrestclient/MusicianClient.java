/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibraryrestclient;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Horak
 */
public class MusicianClient {

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
		System.out.println("Getting all musicians.");
	}
	
	private void update() {
		System.out.println("Updating a musician.");
		String musicianID = CmdLineReader.readInput("ID of musician to update:");
		String name = CmdLineReader.readInput("New name of musician:");
		String biography = CmdLineReader.readInput("New musician's biography:");
		
		System.out.println("MUSICIAN UPDATED SUCCESSFULLY");
	}
	
	private void delete() {
		System.out.println("Deleting a musician.");
		String musicianID = CmdLineReader.readInput("ID of musician to delete:");
		
		System.out.println("MUSICIAN DELETED SUCCESSFULLY");
	}
}

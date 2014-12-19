/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibraryrestclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 
 * @author Horak
 */
public class GenreClient {
	public void processCommand(String command) {
		switch(command) {
			case "getall":
				getAll();
				break;
			case "getcount":
				getCount();
				break;
			case "get":
				getByID();
				break;
			case "insert":
				insert();
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
		try {
			URL url = new URL(App.BASE_URL + "/genres");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "text/plain");

			if (conn.getResponseCode()!= 200) {
				conn.disconnect();
				return;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {

		} catch (IOException e) {

		}
	}
	
	private void getCount() {
		try {
			URL url = new URL(App.BASE_URL + "/genres/count");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "text/plain");

			if (conn.getResponseCode()!= 200) {
				conn.disconnect();
				return;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {

		} catch (IOException e) {

		}
	}
	
	private void getByID() {
		String genreID = CmdLineReader.readInput("ID of genre to get:");
		try {
			URL url = new URL(App.BASE_URL + "/genres/" + genreID);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "text/plain, application/xml, application/json");

			if (conn.getResponseCode()!= 200) {
				conn.disconnect();
				return;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {

		} catch (IOException e) {

		}
	}
	
	private void insert() {
		System.out.println("Updating a genre.");
		String name = CmdLineReader.readInput("New name of genre:");
		String description = CmdLineReader.readInput("New genre's description:");
		
		try {
			URL url = new URL(App.BASE_URL + "/get");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
			osw.write("{\"name\":\"" + name + "\",\"description\":\"" + description + "\"}");
			osw.flush();
			osw.close();

			if (conn.getResponseCode()!= 200) {
				conn.disconnect();
				return;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {

		} catch (IOException e) {

		}
		
		System.out.println("GENRE INSERTED SUCCESSFULLY");
	}

	private void update() {
		System.out.println("Updating a genre.");
		String genreID = CmdLineReader.readInput("ID of genre to update:");
		String name = CmdLineReader.readInput("New name of genre:");
		String description = CmdLineReader.readInput("New genre's description:");
		
		try {
			URL url = new URL(App.BASE_URL + "/genres/" + genreID);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("PUT");
			conn.setRequestProperty("Content-Type", "application/json");
			
			OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
			osw.write("{\"name\":\"" + name + "\",\"description\":\"" + description + "\"}");
			osw.flush();
			osw.close();

			if (conn.getResponseCode()!= 200) {
				conn.disconnect();
				return;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {

		} catch (IOException e) {

		}
		
		System.out.println("GENRE UPDATED SUCCESSFULLY");
	}
	
	private void delete() {
		System.out.println("Deleting a genre.");
		String genreID = CmdLineReader.readInput("ID of genre to delete:");
		
		try {
			URL url = new URL(App.BASE_URL + "/genres/" + genreID);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");

			if (conn.getResponseCode()!= 200) {
				conn.disconnect();
				return;
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {

		} catch (IOException e) {

		}
		
		System.out.println("GENRE DELETED SUCCESSFULLY");
	}
}

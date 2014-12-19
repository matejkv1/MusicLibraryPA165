/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.matejkvassay.musiclibraryrestclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Horak
 */
public class CmdLineReader {

    static private BufferedReader br;

    public static void init() {
        if (br == null) {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
    }

    public static void close() {
        if (br != null) {
            try {
                br.close();
            } catch (IOException ex) {
                Logger.getLogger(CmdLineReader.class.getName()).log(Level.SEVERE, null, ex);
            }
            br = null;
        }
    }

    public static String readInput(String textLine) {
        String input = "";

        try {
            if (textLine != null || !"".equals(textLine)) {
                System.out.println(textLine);
            }

            input = br.readLine();
        } catch (IOException ioe) {
            System.out.println("IO error trying to read your input. Exiting application.");
            System.exit(1);
        }

        return input;
    }
}

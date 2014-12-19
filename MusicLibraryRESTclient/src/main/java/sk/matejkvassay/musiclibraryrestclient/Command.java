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
public class Command {

    private String clsType = "";
    private String command = "";

    public Command(String cmdLnArg) {
        parse(cmdLnArg);
    }

    public String getClsType() {
        return clsType;
    }

    public String getCommand() {
        return command;
    }

    private void parse(String cmdLnArg) {
        cmdLnArg = cmdLnArg.trim();

        int pos = cmdLnArg.indexOf(" ");

        if (pos != -1) {
            clsType = cmdLnArg.substring(0, pos).trim();
            command = cmdLnArg.substring(pos).trim();
        } else {
            clsType = cmdLnArg;
            return;
        }
    }

    @Override
    public String toString() {
        return "Command{" + "clsType=" + clsType + ", command=" + command + '}';
    }
}

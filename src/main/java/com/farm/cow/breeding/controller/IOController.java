package com.farm.cow.breeding.controller;

import com.farm.cow.breeding.io.DataReader;
import com.farm.cow.breeding.io.DataWriter;

public class IOController {
    final String greeting = """
            \nHello, dear user! Type in...
                1 - for creating a farm
                2 parentCowId, childCowId, childNickName - for adding a new female calf to the farm
                3 cowId - for removing a cow from the farm
                4 - for output of entire farm to STDOUT
                5 - to quit from application
            """;
    DataReader in = new DataReader();
    DataWriter out = new DataWriter();

    public void closeResources() {
        in.close();
        out.close();
    }

    public String nextLine() {
        return in.nextLine();
    }

    public void println(String message) {
        out.println(message);
    }

    public String readInput() {
        String input = "";
        try {
            input = in.nextLine().trim();
        } catch (RuntimeException e) {
            out.println(e + " is not a correct input symbol, please try again.");
        } catch (Exception e) {
            out.println("Something went wrong while reading input from terminal, please try again.\n" + e);
        }
        return input;
    }

    public void printGreeting() {
        println(greeting);
    }
}

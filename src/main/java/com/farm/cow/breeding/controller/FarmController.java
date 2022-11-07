package com.farm.cow.breeding.controller;

import com.farm.cow.breeding.service.CowLifeService;
import com.farm.cow.breeding.service.impl.CowLifeBasedOnMapService;

import java.io.*;
import java.math.BigInteger;
import java.util.Scanner;

public class FarmController {
    CowLifeService cowLifeService = new CowLifeBasedOnMapService();
    Scanner in = new Scanner(System.in);
    PrintStream out = System.out;

    char WHITESPACE = ' ';

    String greeting = """
            \nHello, dear user! Type in...
                1 - for creating a farm
                2 parentCowId, childCowId, childNickName - for adding a new female calf to the farm
                3 cowId - for removing a cow from the farm
                4 - for output of entire farm to STDOUT
                5 - to quit from application
            """;

    public void start() {
        boolean userWantsToQuit = false;

        while (!userWantsToQuit) {
            out.println(greeting);
            String input = readInput();
            boolean validationIsGood = validateInputFromCLI(input);

            if (validationIsGood) {
                char symbol = input.charAt(0);
                if (symbol == '1') {
                    initFarm(input);
                }

                if (symbol == '2') {
                    giveBirth(input);
                }

                if (symbol == '3') {
                    endLifeSpan(input);
                }

                if (symbol == '4') {
                    printFarmData(input);
                }

                if (symbol == '5') {
                    userWantsToQuit = true;
                }
            }
        }
    }

    private boolean checkForNonSequentialWhitespaces(String input) {
        long whiteSpacesCount = 0;
        for (int pos = 0, len = input.length(); pos < len; pos++) {
            char symbol = input.charAt(pos);
            if (symbol == WHITESPACE) {
                whiteSpacesCount++;
                pos++;
            }
        }

        if (whiteSpacesCount != 2) {
            out.println("Too many sequential whitespaces between arguments or " +
                    "not enough arguments provided");
            return false;
        }
        return true;
    }

    private void endLifeSpan(String input) {
        String trimmedInput = input.substring(1).trim();
        isAllSymbolsAreDigit(trimmedInput, false);

        BigInteger digitId = new BigInteger(trimmedInput);
        try {
            cowLifeService.removeCow(digitId);
            out.println("Cow with id = " + digitId + " was removed from the farm");
        } catch (RuntimeException e) {
            out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void giveBirth(String input) {
        String name = input.substring(1).trim();
        if (!checkForNonSequentialWhitespaces(name)) {
            return;
        }

        String parentCowId = name.substring(0, name.indexOf(WHITESPACE));
        isAllSymbolsAreDigit(parentCowId, false);
        name = name.substring(parentCowId.length() + 1);

        String childCowId = name.substring(0, name.indexOf(WHITESPACE));
        isAllSymbolsAreDigit(childCowId, false);

        name = name.substring(childCowId.length() + 1);
        isAllSymbolsAreAlphabetic(name);

        try {
            cowLifeService.addCow(new BigInteger(parentCowId), new BigInteger(childCowId), name);
            out.println("Cow with parentId = " + parentCowId + ", id = " + childCowId +
                    ", name = " + name + " was added to the farm");
        } catch (RuntimeException e) {
            out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void initFarm(String input) {
        isAllSymbolsAreDigit(input, false);

        int cowsCount = cowLifeService.getCowsCount();
        String message = "Farm is created. It consists of one cow with id = 0, parentId = -1, name = First Cow";
        if (cowsCount != 0) {
            message = "Farm is already created, if you want to reinitialize it, please restart the application";
        }

        else {
            BigInteger id = BigInteger.ZERO;
            BigInteger parentId = BigInteger.ONE.negate();
            String name = "First Cow";
            cowLifeService.addCow(parentId, id, name);
        }

        out.println(message);
    }

    private boolean isAllSymbolsAreAlphabetic(String input) {
        for (int pos = 0, len = input.length(); pos < len; pos++) {
            char symbol = input.charAt(pos);
            if (!Character.isAlphabetic(symbol)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAllSymbolsAreDigit(String input, boolean allowWhitespaces) {
        for (int pos = 0, len = input.length(); pos < len; pos++) {
            char symbol = input.charAt(pos);
            if (!allowWhitespaces && symbol == WHITESPACE) {
                out.println("Whitespaces are not allowed, first encounter on posiition = " + pos);
                return false;
            }
            else if (!Character.isDigit(symbol) && symbol != WHITESPACE) {
                out.println("Found forbidden symbol '" + symbol + "' on position = " + pos);
                return false;
            }
        }
        return true;
    }

    private boolean isBetweenOneAndFive(String input) {
        boolean ok = true;
        for (int pos = 0, len = input.length(); pos < len; pos++) {
            char symbol = input.charAt(pos);
            if (symbol <= '0' || symbol > '5') {
                out.println("Symbol " + symbol + " on position = " + pos + " is not supported by program, use 1-5");
                ok = false;
            }
        }
        return ok;
    }

    private void printFarmData(String input) {
        isAllSymbolsAreDigit(input, false);
        String data = cowLifeService.getFarmData();
        out.println(data);
    }

    private String readInput() {
        String input = "" ;
        try {
            input = in.nextLine().trim();
        } catch (RuntimeException e) {
            out.println(e + " is not a correct input symbol, please try again.");
        } catch (Exception e) {
            out.println("Something went wrong while reading input from terminal, please try again.\n" + e);
        }
        return input;
    }

    private boolean validateInputFromCLI(String input) {
        return isBetweenOneAndFive(input.substring(0, 1));
    }
}

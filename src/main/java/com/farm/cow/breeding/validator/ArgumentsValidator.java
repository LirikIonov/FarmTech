package com.farm.cow.breeding.validator;

import com.farm.cow.breeding.controller.IOController;

public class ArgumentsValidator {
    char WHITESPACE = ' ';

    public boolean checkForNonSequentialWhitespaces(String input, IOController ioController) {
        long whiteSpacesCount = 0;
        for (int pos = 0, len = input.length(); pos < len; pos++) {
            char symbol = input.charAt(pos);
            if (symbol == WHITESPACE) {
                whiteSpacesCount++;
                pos++;
            }
        }

        if (whiteSpacesCount != 2) {
            ioController.println("Too many sequential whitespaces between arguments or " +
                    "not enough arguments provided");
            return false;
        }
        return true;
    }

    public boolean isAllSymbolsAreAlphabeticOrDigit(String input, IOController ioController) {
        for (int pos = 0, len = input.length(); pos < len; pos++) {
            char symbol = input.charAt(pos);
            if (!Character.isAlphabetic(symbol) && !Character.isDigit(symbol)) {
                ioController.println("Symbol " + symbol + " on position = " + pos +
                        " is not allowed. String (" + input + ")");
                return false;
            }
        }
        return true;
    }

    public boolean isAllSymbolsAreDigit(String input, boolean allowWhitespaces, IOController ioController) {
        for (int pos = 0, len = input.length(); pos < len; pos++) {
            char symbol = input.charAt(pos);
            if (!allowWhitespaces && symbol == WHITESPACE) {
                ioController.println("Whitespaces are not allowed, first encounter " +
                        "on position = " + pos);
                return false;
            }
            else if (!Character.isDigit(symbol) && symbol != WHITESPACE) {
                ioController.println("Found forbidden symbol " + symbol +
                        " on position = " + pos);
                return false;
            }
        }
        return true;
    }
    public boolean isBetweenOneAndFive(String input, IOController ioController) {
        boolean ok = true;
        for (int pos = 0, len = input.length(); pos < len; pos++) {
            char symbol = input.charAt(pos);
            if (symbol <= '0' || symbol > '5') {
                ioController.println("Symbol " + symbol + " on position = " +
                        pos + " is not supported by program, use 1-5");
                ok = false;
            }
        }
        return ok;
    }

}

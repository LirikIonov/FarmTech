package com.farm.cow.breeding.controller;

import com.farm.cow.breeding.service.CowLifeService;
import com.farm.cow.breeding.service.impl.CowLifeServiceImpl;
import com.farm.cow.breeding.validator.ArgumentsValidator;

import java.math.BigInteger;

public class FarmController {
    ArgumentsValidator argValidator = new ArgumentsValidator();
    CowLifeService cowLifeService = new CowLifeServiceImpl();
    IOController ioController = new IOController();

    public void start() {
        boolean userWantsToQuit = false;

        while (!userWantsToQuit) {
            ioController.printGreeting();
            String input = ioController.readInput();
            boolean validationIsGood = validateInputFromCLI(input);

            if (validationIsGood) {
                char symbol = input.charAt(0);
                switch (symbol) {
                    case '1' -> initFarm(input);
                    case '2' -> giveBirth(input);
                    case '3' -> endLifeSpan(input);
                    case '4' -> printFarmData(input);
                    case '5' -> userWantsToQuit = true;
                }
            }
        }

        ioController.closeResources();
    }

    private void endLifeSpan(String input) {
        String trimmedInput = input.substring(1).trim();
        boolean argParsed = argValidator.isAllSymbolsAreDigit(trimmedInput, false, ioController);

        if (argParsed) {
            BigInteger digitId = new BigInteger(trimmedInput);
            try {
                cowLifeService.removeCow(digitId);
                ioController.println("Cow with id = " + digitId + " was removed from the farm");
            } catch (RuntimeException e) {
                ioController.println(e.getMessage());
            }
        }
    }

    private String substringUntilFirstWhiteSpace(String input) {
        return input.substring(0, input.indexOf(' '));
    }

    private void giveBirth(String input) {
        String name = input.substring(1).trim();
        if (!argValidator.checkForNonSequentialWhitespaces(name, ioController)) {
            return;
        }

        String parentCowId = substringUntilFirstWhiteSpace(name);
        boolean firstParsed = argValidator.isAllSymbolsAreDigit(parentCowId,
                false, ioController);
        name = name.substring(parentCowId.length() + 1);

        String childCowId = substringUntilFirstWhiteSpace(name);
        boolean secondParsed = argValidator.isAllSymbolsAreDigit(childCowId, false, ioController);

        name = name.substring(childCowId.length() + 1);
        boolean thirdParsed = argValidator.isAllSymbolsAreAlphabeticOrDigit(name, ioController);

        if (firstParsed && secondParsed && thirdParsed) {
            try {
                cowLifeService.addCow(new BigInteger(parentCowId), new BigInteger(childCowId), name);
                ioController.println("Cow with parentId = " + parentCowId + ", id = " + childCowId +
                        ", name = " + name + " was added to the farm");
            } catch (RuntimeException e) {
                ioController.println(e.getMessage());
            }
        }
    }

    private void initFarm(String input) {
        boolean argParsed = argValidator.isAllSymbolsAreDigit(input, false, ioController);

        if (argParsed) {
            int cowsCount = cowLifeService.getCowsCount();
            String message = "Farm is created. It consists of one cow with id = 0, parentId = -1, name = First Cow";
            if (cowsCount != 0) {
                message = "Farm is already created, if you want to reinitialize it, please restart the application";
            } else {
                BigInteger id = BigInteger.ZERO;
                BigInteger parentId = BigInteger.ONE.negate();
                String name = "First Cow";
                cowLifeService.addCow(parentId, id, name);
            }

            ioController.println(message);
        }
    }

    private void printFarmData(String input) {
        boolean argParsed = argValidator.isAllSymbolsAreDigit(input, false, ioController);
        if (argParsed) {
            String data = cowLifeService.getFarmData();
            ioController.println(data);
        }
    }

    private boolean validateInputFromCLI(String input) {
        return argValidator.isBetweenOneAndFive(input.substring(0, 1), ioController);
    }
}

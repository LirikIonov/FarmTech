package com.farm.cow.breeding.io;

import java.io.PrintStream;
import java.util.Scanner;

public class DataReader {
    Scanner reader = new Scanner(System.in);

    public void close() {
        reader.close();
    }

    public String nextLine() {
        return reader.nextLine();
    }
}

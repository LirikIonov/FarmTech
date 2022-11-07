package com.farm.cow.breeding.io;

import java.io.PrintStream;

public class DataWriter {
    PrintStream writer = System.out;

    public void close() {
        writer.close();
    }

    public void println(String message) {
        writer.println(message);
    }
}

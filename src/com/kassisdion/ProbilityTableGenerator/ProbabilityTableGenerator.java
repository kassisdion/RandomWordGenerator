package com.kassisdion.ProbilityTableGenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProbabilityTableGenerator {

    private final ArrayList<Character> mTokens; //Simple token representation {"a, b, c, d, e, f, ...}
    private final Scanner mIn; //Input
    private final int N; //mTable length
    private double[][] mTable; //Token representation in 2D : mTable[i][j] is the probability that mTokens[i] is follow by mTokens[j]
    private Boolean mVerbose; //Boolean for displaying log

    /*
    ** Constructor
     */
    public ProbabilityTableGenerator(final Scanner in, final ArrayList<Character> tokens) {
        if (in == null) {
            throw new IllegalArgumentException("Scanner(in) can't be null");
        } else if (tokens == null) {
            throw new IllegalArgumentException("String(token) can't be null");
        } else if (tokens.size() < 3) {
            throw new IllegalArgumentException("String(token).length should be > 2");
        } else if (tokens.contains('\0')) {
            throw new IllegalArgumentException("String(token) cannot contain \\0");
        }

        //Init private field
        mTokens = tokens;
        mTokens.add('\0');//for empty

        mIn = in;
        N = tokens.size();
        mTable = new double[N][N];
        mVerbose = false;
    }

    public ProbabilityTableGenerator(final String inputPath, final ArrayList<Character> tokens) throws FileNotFoundException {
        this(new Scanner(new File(inputPath), "UTF-8"), tokens);
    }

    /*
    ** Private method
     */
    public double[][] buildTable() {
        /*
        ** Parse input file and complete the matrix
         */
        printLog("Parsing file...");

        // We loop over each word
        while (mIn.hasNextLine()) {

            String word = mIn.nextLine();
            printLog("Current word: " + word);

            try {
                // Link the first char with 'empty' char
                mTable[indexOfToken('\0')][indexOfToken(word.charAt(0))] += 1;

                // We loop over each character and we complete the table
                for (int i = 1; i < word.length(); i++) {
                    mTable[indexOfToken(word.charAt(i - 1))][indexOfToken(word.charAt(i))] += 1;
                }
                // Link the last char with 'empty' char
                mTable[indexOfToken(word.charAt(word.length() - 1))][indexOfToken('\0')] += 1;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Unauthorized char in word: " + word);
            }
        }

        /*
        ** We now have to convert the matrix into a stochastic matrix
         */
        printLog("Generating matrix...");

        //Loop over each row
        for (int i = 0; i < N; i++) {
            //Calculate the weight of the current row
            double weight = 0;
            for (int j = 0; j < N; j++) {
                weight += mTable[i][j];
            }

            printLog("" + mTokens.get(i) + " has been followed " + weight + " times");

            //Convert every cell in the current row into probability
            for (int j = 0; j < N; j++) {
                printLog("" + mTable[i][j] + " times by " + mTokens.get(j));
                mTable[i][j] = mTable[i][j] / weight;
            }
        }

        return mTable;
    }

    public double[][] getTable() {
        return mTable;
    }

    /*
    ** public method
     */
    public void setVerbose(final Boolean verbose) {
        mVerbose = verbose;
    }

    public void printReadableTable() {
        for (char token : mTokens) {
            System.out.printf("%8c", token);
        }
        System.out.printf("\n");

        for (int i = 0; i < N; i++) {
            System.out.printf("%c   ", mTokens.get(i));
            for (int j = 0; j < N; j++) {
                System.out.printf("%.5f ", mTable[i][j]);
            }
            System.out.printf("\n");
        }

        System.out.flush();
    }

    /*
    ** Utils
     */
    //return the position of the given token in mTokens or -1 of not found
    private int indexOfToken(final char token) {
        for (int i = 0; i < mTokens.size(); i++) {
            if (mTokens.get(i) == token) {
                return i;
            }
        }
        return -1;
    }

    private void printLog(final String log) {
        if (mVerbose) {
            System.out.println(log);
        }
    }
}
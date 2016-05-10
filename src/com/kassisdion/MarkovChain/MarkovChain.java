package com.kassisdion.MarkovChain;


import java.util.Random;

public class MarkovChain {
    private final double[][] mProbabilityTable;
    private final char[] mTokens;
    private final Random mRandom;

    public MarkovChain(double[][] probabilityTable, final char[] tokens) {
        mProbabilityTable = probabilityTable;
        mTokens = tokens;
        mRandom = new Random();
    }

    public String generateWord(final int size) {
        int state = mRandom.nextInt(mTokens.length);

        String word = "";

        //run Markov chain
        while (word.length() < size) {
            double r = Math.random();
            double sum = 0.0;

            for (int j=0; j < mProbabilityTable[state].length; j++) {
                sum += mProbabilityTable[state][j];
                if (r <= sum) {
                    word += mTokens[j];
                    state = j;
                    break;
                }
            }
        }

        return word;
    }
}

package com.kassisdion.MarkovChain;

import java.util.List;

public class MarkovChain {
    private final double[][] mProbabilityTable;
    private final List<Character> mTokens;

    public MarkovChain(double[][] probabilityTable, final List<Character> tokens) {
        mProbabilityTable = probabilityTable;
        mTokens = tokens;
    }

    public String generateWord() {
        int state = mTokens.indexOf('\0');

        String word = "";

        //run Markov chain
        while (word.length() == 0 || word.charAt(word.length() - 1) != '\0') {
            double r = Math.random();
            double sum = 0.0;

            // determine next state
            for (int j=0; j < mTokens.size(); j++) {
                sum += mProbabilityTable[state][j];
                if (r < sum) {
                    state = j;
                    word += mTokens.get(j);
                    break;
                }
            }
        }

        return word;
    }
}

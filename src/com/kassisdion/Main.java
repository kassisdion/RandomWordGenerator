package com.kassisdion;

import com.kassisdion.MarkovChain.MarkovChain;
import com.kassisdion.ProbilityTableGenerator.ProbabilityTableGenerator;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) {

        try {
            String inputFileName = "media/testInput/COMPOUND.TXT";
            char[] tokens = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

            ProbabilityTableGenerator probabilityTableGenerator = new ProbabilityTableGenerator(inputFileName, tokens);

            probabilityTableGenerator.buildTable();
            probabilityTableGenerator.printReadableTable();

            MarkovChain markovChain = new MarkovChain(probabilityTableGenerator.getTable(), tokens);
            for (int i=0; i < 10; i++) {
                System.out.println("word :" + markovChain.generateWord(3));
            }

        } catch (FileNotFoundException e) {
            System.out.println("Invalid or no input file");
        }

    }
}

package com.kassisdion;

import com.kassisdion.MarkovChain.MarkovChain;
import com.kassisdion.ProbilityTableGenerator.ProbabilityTableGenerator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        try {
            String inputFileName = "media/testInput/COMPOUND.TXT";
            ArrayList<Character> tokens = new ArrayList<>(Arrays.asList(
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                    'à', 'è', 'ù', 'é', 'ê', 'â', 'î', 'û', 'ô', 'ï', 'ë', 'ü', 'ö',
                    'ç', '-'));

            ProbabilityTableGenerator probabilityTableGenerator = new ProbabilityTableGenerator(inputFileName, tokens);
            probabilityTableGenerator.setVerbose(false);

            probabilityTableGenerator.buildTable();
            probabilityTableGenerator.printReadableTable();

            MarkovChain markovChain = new MarkovChain(probabilityTableGenerator.getTable(), tokens);
            for (int i = 0; i < 10; i++) {
                System.out.println("word :" + markovChain.generateWord());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Invalid or no input file");
        }

    }
}

# RandomWordGenerator
A Java program that generates random names using Markov chain (we parse plain text files to generate probability tables and then we use them)..

## Usage

### 1) Building the probability tables
You can use the 'ProbabilityTableGenerator' class like this :
```java
String inputFileName = "testInput/COMPOUND.TXT";
char[] tokens = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

ProbabilityTableGenerator probabilityTableGenerator = new ProbabilityTableGenerator(inputFileName, tokens);

probabilityTableGenerator.buildTable();
probabilityTableGenerator.printReadableTable();
```

Where inputFileName is a path to a file containing a list of word and tokens is the Alphabet used by the list.

/!\ inputFile should respect some characteristics /!\ : 
* Language : Each word has to be from the same language
* Syntax: Each word should be on a separated line
* Representativenes: Your list should be representative (Your data should include rare letter combinations, but these should be infrequent).

It will give you an input which looks like this : 
![output](https://github.com/kassisdion/RandomWordGenerator/blob/master/media/output/output.png?raw=true)

### 2) Generating the random word
Once you got the probability table, you can use the 'MarkovChain' class like this:
```java
MarkovChain markovChain = new MarkovChain(probabilityTableGenerator.getTable());
markovChain.generateWord();
```

## Improvements

* Build an average word length of the list.
* Prevent long repetition of a single letter.
* Take letter position into consideration (the first and the last letter).


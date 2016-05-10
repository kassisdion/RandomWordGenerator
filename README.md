# RandomWordGenerator

## What is RandomWordGenerator ?
A Java program that generates random names using Markov chain.
We parse plain text files to generate probability tables [(stochastic matrix)](https://en.wikipedia.org/wiki/Stochastic_matrix) and then we use them to generate some random word with the [Markov chain algorithm](https://en.wikipedia.org/wiki/Markov_chain)

## Usage
### 1) Building the probability tables
You can use the 'ProbabilityTableGenerator' class like this :
```java
try {
    String inputFileName = "media/testInput/COMPOUND.TXT";
    ArrayList<Character> tokens = new ArrayList<>(Arrays.asList(
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'à', 'è', 'ù', 'é','ê','â', 'î', 'û', 'ô', 'ï', 'ë', 'ü', 'ö',
            'ç','-'));

    ProbabilityTableGenerator probabilityTableGenerator = new ProbabilityTableGenerator(inputFileName, tokens);
    probabilityTableGenerator.setVerbose(false);

    probabilityTableGenerator.buildTable();
    probabilityTableGenerator.printReadableTable();
    }catch (FileNotFoundException e) {
            System.out.println("Invalid or no input file");
    }
```

Where inputFileName is a path to a file containing a list of word and tokens is the Alphabet used by the list.

/!\ inputFile should respect some characteristics /!\ : 
* Language : Each word has to be from the same language
* Syntax: Each word should be on a separated line
* Representativenes: Your list should be representative (Your data should include rare letter combinations, but these should be infrequent).

It will give you an output like this : 
![output](https://github.com/kassisdion/RandomWordGenerator/blob/master/media/output/output.png?raw=true)

### 2) Generating the random word
Once you got the probability table, you can use the 'MarkovChain' class like this:
```java
MarkovChain markovChain = new MarkovChain(probabilityTableGenerator.getTable(), tokens);
System.out.println("word :" + markovChain.generateWord());
```

## Improvements
* Build an average word length of the list.
* Prevent long repetition of a single letter.
* Use 2 char instead of one when generating a word (ie: generate probability table on 2 char)

## License
See  LICENSE.txt

## Contact
If you have any new idea about this project, feel free to [contact me](mailto:florian.faisant@gmail.com).

package comprehensive;

import assign08.MazeGen;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class GenerateGrammarFile {
    private Scanner fileReader = new Scanner(new File("src/comprehensive/poetic_sentence.g"));
    private StringBuilder phrase;
    private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

    public GenerateGrammarFile() throws FileNotFoundException {
    }

    /**
     *
     * @param filename
     */
    public static void createGrammarFile(String filename){
        try {
            PrintWriter output = new PrintWriter(new FileWriter(filename));
            output.println();


            output.println();
            output.close();
        } catch(Exception e){e.printStackTrace();}
    }

    /**
     * Creating multiple terminals!
     */

    // store everything into hashmap (non terminals as keys) and (production rules)
    // if non terminal is a verb, then add object non terminal at end of each verb phrase possible.

    public void generateMap(int numOfNonTerminalsInInputGrammar) { // Map = Hash Map
        String currentLine = fileReader.nextLine();
        String nonTerminal;
        while (fileReader.hasNextLine()) {
            if (this.isNonTerminal(currentLine)) {
                nonTerminal = currentLine;
                currentLine = fileReader.nextLine();
                ArrayList<String> valueList = map.get(nonTerminal);
                while (!(currentLine.equals("}"))) { //add every line after the non-terminal header but before the ending curly brace to the ArrayList for that non-terminal object
                    for (int i = 1; i <= numOfNonTerminalsInInputGrammar; i++) {
                        valueList.add(currentLine);
                    }
                    currentLine = fileReader.nextLine();
                }

                // inside current line add current line + "<object>" on any random lines N times
            } else {
                currentLine = fileReader.nextLine(); //if the line is not a non-terminal or a line contained within the non-terminal options, pass over it.
            }
        }
    }

    /**
     * This helper method checks if the parameter value is a non-terminal
     * by checking if it has the required '<' opening bracket, as well as
     * if it contains any illegal whitespaces. If the value is a non-terminal,
     * it adds the value and an empty ArrayList of strings to the HashMap to be
     * stored for later use. It returns true if the value is a non-terminal, false if not.
     *
     * @param currentLine The line that is checked for non-terminal characteristics
     * @return True if the parameter value is a non-terminal, false otherwise.
     */
    private boolean isNonTerminal(String currentLine) {
        if (currentLine.length() == 0) {
            return false;
        }
        if (currentLine.charAt(0) == '<') {
            if (currentLine.contains(" ")) { //if any whitespaces are contained within the non-terminal brackets, it is an illegal argument and cannot be used
                return false;
            }
            map.put(currentLine, new ArrayList<String>());
            return true;
        }
        return false;
    }
}

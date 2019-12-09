import java.io.*;
import java.util.ArrayList;

/**
 * Author: Mason Waters
 * Date: 12/8/2019
 * Hashing Assignment
 * This is the Game class
 * In collaboration with: Blake Furlano and Robert Hable
 */
public class Game {
    /**
     * This method is the main method that both runs the game and uses helper methods to build the hash chain
     * and also gets all input from the user necessary for the game
     * @param args the array from the command line that gets the desired input
     */
    public static void main(String[] args) {
        HashChain<String, String> hash = new HashChain<String, String>(3889);
        HashChain<String, String> generatedWords = new HashChain<>(5001);
        String startWord = "";
        String endWord = "";
        int tryHops = 0;
        if (args.length == 3) {
            startWord = args[0];
            endWord = args[1];
            tryHops = Integer.parseInt(args[2]);
        } else {
            System.out.println("Please enter in the required number of fields as follows: ");
            System.out.println("<startWord> <endingWord> <NumberOfHops>");
            System.out.println("Thanks and goodbye.");
            System.exit(0);
        }
        openAndReadFile("dictionary-1.txt", hash);
        if ((!checkWord(startWord, hash) || !checkWord(endWord, hash)) || startWord.length() != endWord.length()) {
            System.out.println("Words are not the same length or not in dictionary.");
            System.out.println("There is no solution.");
            System.exit(0);
        }
        TNode tStart = new TNode(startWord, null);
        TNode tEnd = new TNode(endWord, null);
        QueueList<TNode> masterQ = new QueueList<>();
        generatedWords.insert(new HashNode<>(startWord, startWord));
        masterQ.enqueue(tStart);//now we know that whenever we hit a node with its parent null, it is the root/start
        boolean found = false;
        ArrayList<String> walkItBack = new ArrayList<>();
        while (!found) {
            TNode workWith = masterQ.dequeue();
            TNode foundNode = createAndCheckNodes(workWith, hash, masterQ, endWord, generatedWords);
            if (!(foundNode == null)) {
                walkItBack.add(endWord);
                while (foundNode.getParent() != null) {
                    walkItBack.add(foundNode.getElement().toString());
                    foundNode = foundNode.getParent();
                }
                String endStatement = startWord + " ";
                for (int i = walkItBack.size() - 1; i >= 0; i--) {
                    endStatement += walkItBack.get(i) + " ";
                }
                if (walkItBack.size() > tryHops) {
                    System.out.println("Solution may be beyond given depth.");
                    System.out.println("There is no solution.");
                } else {
                    System.out.println("Can make it in " + walkItBack.size() + " hops.");
                    System.out.println(endStatement);
                }
                found = true;//set true once we find a word that matches the endword
            }
        }
    }

    /**
     * This method calls a word and the hash chain and checks the hash chain for said word
     * @param word This is the word called to be checked for
     * @param hash is the hash chain called to check for a word
     * @return returns null if the word isn't found
     */
    public static boolean checkWord(String word, HashChain<String, String> hash) {
        HashNode hnStart = hash.search(word, word);
        if (hnStart != null) {
            return true;
        }
        return false;
    }

    /**
     * This method opens and reads the file that is passed to it then inserts the word into the
     * hash chain
     * @param fileName is the file name passed to the methof
     * @param h        is the HashChain called to insert into
     */
    private static void openAndReadFile(String fileName, HashChain h) {
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitStr = line.split(" ");
                for (int i = 0; i < splitStr.length; i++) {
                    h.insert(new HashNode(splitStr[i], splitStr[i]));
                }
            }
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method creates nodes for newly inserted words and checks existing ones for compatibility
     * @param parent         This is the parent node called from the TNode class
     * @param hash           The string called from the Hash Chain
     * @param masterQ        This is the main queue called that stores the ued words
     * @param endWord        is the last word called from the args array
     * @param generatedWords a hash chain of generated words called
     * @return returns null to insinuate that the word couldn't be built
     */
    public static TNode createAndCheckNodes(TNode parent, HashChain<String, String> hash, QueueList<TNode> masterQ, String endWord, HashChain<String, String> generatedWords) {
        String word = parent.getElement().toString();
        int found = -1;
        Character[] wordArray = new Character[word.length()];
        for (int i = 0; i < wordArray.length; i++) {
            wordArray[i] = word.charAt(i);
        }
        int originalValue = 0;
        for (int i = 0; i < wordArray.length; i++) {
            originalValue = wordArray[i];
            //System.out.println(originalValue);
            for (int j = 97; j <= 122; j++) {
                if (!(j == originalValue)) {
                    int a = j;
                    Character[] hold = wordArray;
                    char b = (char) a;
                    hold[i] = b;
                    String newWord = buildWord(hold).trim();
                    if (hash.search(newWord, newWord) != null) {
                        if (newWord.equalsIgnoreCase(endWord)) {
                            return parent;
                        }
                        if (generatedWords.search(newWord, newWord) == null) {
                            masterQ.enqueue(new TNode(newWord, parent));
                            generatedWords.insert(new HashNode<>(newWord, newWord));
                        }
                    }
                }
            }
            wordArray[i] = (char) originalValue;
        }
        return null;
    }

    /**
     * This method builds a word when passed a word
     * @param word is the word called from the Character array
     * @return returns the newly built word
     */
    public static String buildWord(Character[] word) {
        String builtWord = "";
        for (int i = 0; i < word.length; i++) {
            builtWord += word[i];
        }
        return builtWord;
    }
}
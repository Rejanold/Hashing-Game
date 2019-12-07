import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws FileNotFoundException{
        /*
        Character[] test = new Character[]{'b','l','a','k','e'};
        System.out.println(buildWord(test));

         */

        HashChain<String, String> hash = new HashChain<String,String>(3889);
        HashChain<String, String> generatedWords = new HashChain<>(5001);
        String startWord = "";
        String endWord = "";
        int tryHops = 0;
        if(args.length == 3){
            startWord = args[0];
            endWord = args[1];
            tryHops = Integer.parseInt(args[2]);
        }
        else{
            System.out.println("Please enter in the required number of fields as follows: ");
            System.out.println("<startWord> <endingWord> <NumberOfHops>");
            System.out.println("Thanks and goodbye :)");
            System.exit(0);
        }

        openAndReadFile("dictionary-1.txt", hash);
        //if problem, mess around with the if statement below
        if((!checkWord(startWord, hash) || !checkWord(endWord, hash)) || startWord.length() != endWord.length()){
            System.out.println("The words passed were either not the same length or not words at all.");
            System.out.println("There is no solution.");
            System.exit(0);
        }
        TNode tStart = new TNode(startWord, null);
        TNode tEnd = new TNode(endWord, null);

        QueueList<TNode> masterQ = new QueueList<>();
        generatedWords.insert(new HashNode<>(startWord,startWord));
        masterQ.enqueue(tStart);//now we know that whenever we hit a node with its parent null, it is the root/start
        boolean found = false;
        //System.out.println("********************************************************************");
        ArrayList<String> walkItBack = new ArrayList<>();
        while(!found){
            TNode workWith = masterQ.dequeue();
            //System.out.println("FIRST " + workWith.getElement().toString());
            TNode foundNode = createAndCheckNodes(workWith, hash, masterQ, endWord, generatedWords);
            if(!(foundNode == null)){//if we found the word, begin trace
                // will need to store the values of each node we trace to print later
                //arraylist would be best most likely for dynamic size
                //System.out.println("made it");
                //walkItBack.add(foundNode.getElement().toString());
                walkItBack.add(endWord);
                while(foundNode.getParent()!=null){
                    walkItBack.add(foundNode.getElement().toString());
                    foundNode = foundNode.getParent();
                }
                //print it out
                String endStatment = startWord + " ";
                for(int i = walkItBack.size() -1; i >=0; i--){
                    endStatment += walkItBack.get(i) + " ";
                }
                System.out.println("Found the word in " + walkItBack.size() + " hops.");
                System.out.println(endStatment);
                found = true;//set true once we find a word that matches the endword
            }
            //System.out.println("THIS " + workWith.getElement().toString());

        }



    }

    public static boolean checkWord(String word, HashChain<String, String> hash) {
        HashNode hnStart = hash.search(word, word);
        if (hnStart != null) {
            return true;
        }
        return false;
    }

    private static void openAndReadFile(String fileName, HashChain h){
        String line = null;

        try{
            FileReader fileReader = new FileReader(fileName);

            //wrap it in a buffered reader
            BufferedReader bufferedReader= new BufferedReader(fileReader);
            while((line=bufferedReader.readLine() ) != null){

                //split the string into tokens based on space
                String[] splitStr = line.split(" ");
                for(int i = 0; i<splitStr.length; i++){
                    h.insert(new HashNode(splitStr[i], splitStr[i] ));
                }

            }
            bufferedReader.close();

        }
        catch(IOException ex){
            ex.printStackTrace();
        }

    }


    public static TNode createAndCheckNodes(TNode parent, HashChain<String, String> hash, QueueList<TNode> masterQ, String endWord, HashChain<String,String> generatedWords){
        String word = parent.getElement().toString();
        int found = -1;
        Character[] wordArray = new Character[word.length()];
        for(int i = 0; i < wordArray.length; i++){
            wordArray[i] = word.charAt(i);
        }//need loop
        int originalValue = 0;
        for(int i = 0; i < wordArray.length; i++) {//loops through each letter in parent
            originalValue = wordArray[i];
            System.out.println(originalValue);

            for(int j = 97; j <= 122; j++){
                if(!(j == originalValue)){
                    int a = j;
                    Character[] hold = wordArray;
                    char b = (char)a;
                    hold[i] = b;

                    //check to see if the character array is actually a word
                    String newWord = buildWord(hold).trim();
                    //if it is we create a new tnode and set its parent to parent passed here
                    if(hash.search(newWord,newWord) != null){
                        //if reach here, add word to Q unless its the endword
                        if(newWord.equalsIgnoreCase(endWord)){
                            //trace back to root and count by starting at the parent
                            //will do this in another method called inside main somewhere
                            return parent;
                        }
                        if(generatedWords.search(newWord,newWord) == null){
                            masterQ.enqueue(new TNode(newWord, parent));
                            generatedWords.insert(new HashNode<>(newWord, newWord));
                        }



                    }


                }
            }
            wordArray[i] = (char)originalValue;
        }
        return null;

    }

    public static String buildWord(Character[] word){
        String builtWord = "";
        for(int i = 0; i < word.length; i++){
            builtWord +=  word[i];
        }
        return builtWord;
    }


}

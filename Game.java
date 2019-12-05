import java.io.*;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws FileNotFoundException{
        HashChain<String, String> hash = new HashChain<String,String>(3889);
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
        if(!checkWords(startWord,endWord, hash)){
            System.out.println("The words passed were either not the same length or not words at all.");
            System.out.println("There is no solution.");
            System.exit(0);
        }
        TNode tStart = new TNode(startWord, null);
        TNode tEnd = new TNode(endWord, null);



    }
    public static boolean checkWords(String startWord, String endWord, HashChain<String, String> hash){
        if(startWord.length() == endWord.length()){
            HashNode hnStart = hash.search(startWord,startWord);
            HashNode hnEnd = hash.search(endWord,endWord);
            if(hnStart == null || hnEnd == null){
                return false;
            }
            else{
                return true;
            }
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
    public static void createAndCheckNodes(TNode parent){
        String word = parent.getElement().toString();
        Character[] wordArray = new Character[word.length()];
        for(int i = 0; i < wordArray.length; i++){
            wordArray[i] = word.charAt(i);
        }//need loop
        int originalValue = 0;
        for(int i = 0; i < wordArray.length; i++) {
            originalValue = wordArray[i];
            for(int j = 97; j <= 122; j++){
                if(!(j == originalValue)){
                    int a = j;
                    Character[] hold = wordArray;
                    char b = (char)a;
                    hold[i] = b;
                    //check to see if the character array is actually a word
                    //if it is we create a new tnode and set its parent to parent passed here
                    //if not then we do nothing and move onto the next letter
                    
                }
            }
        }

    }
    /*
    public static String buildWord(String[] word){

    }

     */
}

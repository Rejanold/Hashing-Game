import java.io.*;
/**Driver demonstrating a Hash Table
*/
public class MakeAHashTableChaining{

	public static void main(String[] args){
		
		HashChain<String, String> hash = new HashChain<String, String>(13);
		HashChain<Integer, String> hash2 = new HashChain<Integer, String>(13);
		if (args.length == 1) {
			//open file and load table
			openAndReadFile(args[0], hash);
			openAndReadFileForInts(args[0], hash2);
		}	
		else{
			System.out.println("invalid number of arguments passed.  Ending.");
			System.exit(0);	// End gracefully.
			
		}
		//for debugging purposes
		SLList<HashNode>[] table = hash.getChainTable();
		for(int i = 0; i < table.length; i++){
			SLList<HashNode> l = table[i];
			System.out.println("Things that hashed to position " + i + ": "+ l.toString());
		}
		HashNode hn = hash.search("zoo", "zoo");
		hash.remove("zoo", "zoo");
		hn = hash.search("zoo", "zoo");
				
		SLList<HashNode>[] table2 = hash2.getChainTable();
		for(int i = 0; i < table2.length; i++){
			SLList<HashNode> l = table2[i];
			System.out.println("Things that hashed to position " + i + ": "+ l.toString());
		}
		HashNode hn2 = hash2.search(349, "zoo");
		hash2.remove(349, "zoo");
		hn2 = hash2.search(349, "zoo");
	}
	//using the sting as the key and value
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
	//using an integer key
	private static void openAndReadFileForInts(String fileName, HashChain h){
		String line = null;
		int counter = 0;
		try{
			FileReader fileReader = new FileReader(fileName);
			
			//wrap it in a buffered reader
			BufferedReader bufferedReader= new BufferedReader(fileReader);
			while((line=bufferedReader.readLine() ) != null){
				
				//split the string into tokens based on space
				String[] splitStr = line.split(" ");
				for(int i = 0; i<splitStr.length; i++){
					h.insert(new HashNode(counter, splitStr[i] ));
					counter++;
				}
	
			}
			//System.out.println(counter);
			bufferedReader.close();
		
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		
	}	
		
}

		
		

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;
import java.util.Collections;
public class FileIo {

    // instance variables 
    Set<String> words = new HashSet<>(); // Set for storing all the different words from reading the file 
    Map<String,Word> wordMap = new HashMap<>(); //HashMap for storing the words and their frequencies
    ArrayList<Word> wordList = new ArrayList<>(); //List for storing the words 
    String filename; 
    Set<String> getWords() {
        return words;
    }

    Map<String,Word> getWordMap() {
        return wordMap;
    }

    ArrayList<Word> getWordList() {
        return wordList;
    }

    // Ensures the file that was passed in is valid
    boolean openTxtfile(String filename){
        this.filename = filename;
        try{
            BufferedReader fr = new BufferedReader(new FileReader(filename));
            return true;
        }
        catch(IOException e){
            return false;
        }
    }
    // Helper Function which adds the words to the set and the map
    void addWord(String word, String filename){
        

        if(wordMap.containsKey(word)){
            Word w = wordMap.get(word);
            if(filename.substring(0,2).equals("PT")){
                w.incrementPT();

            }
            else{
                w.incrementYT();
            }
            wordMap.put(word,w);
        }

        else{
            Word newword = new Word(word);
            if(filename.substring(0,2).equals("PT")){
                newword.incrementPT();
            }
            else{
                newword.incrementYT();
            }
            wordMap.put(word,newword); 
        }


    }
    //Reads the file and adds the words to the set and the map
    void readTxtfile(String filename){
        ArrayList<String> exceptions = new ArrayList<>();
        try{
            BufferedReader fr = new BufferedReader(new FileReader(filename));
            String line = fr.readLine();
            boolean valid = true; 
            while(line != null){
                
                String[] words_line  = line.split(" ");
                for(String word : words_line){
                    valid = true; 
                    for(int i = 0; i<word.length(); i++){
  
                        if(word.charAt(i) == '\''){
                            continue;
                        }
                        if((word.charAt(i) == ',' || word.charAt(i) == '.' || word.charAt(i)== '?') && i == word.length()-1){
                            valid = false;

                            words.add(word.substring(0,i).toLowerCase());
                            //addWord(word.substring(0,i), filename);
                            exceptions.add(word.substring(0,i));
                            break; 
                        }
                        else if(!Character.isLetter(word.charAt(i)) ){
                        
                            
                            valid = false;
                            break;
                        }
                        if(word.charAt(i)=='['){
                            for(int j = i; j<word.length(); j++){
                                if(word.charAt(j)==']'){
                                    valid = false; 
                                    words.add(word.substring(i+1,j));
                                    addWord(word.substring(i+1,j), filename);
                                }
                            }
                            if(!valid){
                                continue;
                            }
                        }
                        if(!Character.isLetter(word.charAt(i))){
                            valid = false; 
                            break; 
                        }
                    }
                    
                    if(valid && !word.equals("")){
                        word = word.toLowerCase();
                       // System.out.println(word);
                        words.add(word);
                        addWord(word,filename);
                    }

                }
                line = fr.readLine();
                 
            }

            fr.close();
        }
        catch(IOException e){
            System.out.println("Error");
        }

        for( int e = 0; e<exceptions.size(); e++){
            exceptions.set(e, exceptions.get(e).toLowerCase());
            addWord(exceptions.get(e),filename);
            
        }
        }

    //Writes all the words in both files to debug1.txt as the words are added into the set which does not take duplicates. 
    void writeTxtfile(){

        filename = "debug1.txt";
        try{
            BufferedWriter fw = new BufferedWriter(new FileWriter(filename));
            ArrayList<String> sorted_words = new ArrayList<>(words);
            Collections.sort(sorted_words);
            for(String word : sorted_words){
                fw.write(word + "\n");
                wordList.add(wordMap.get(word));
            }
            fw.close();
        }
        catch(IOException e){
            System.out.println("Error");
        }

    }
    }




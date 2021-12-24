import java.util.Map;
import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.ArrayList;
 
public class Hashing {
    //create a hash map with alphabet as keys and the order does not matter however make each key have a binary search tree 
    private Map<Character, BST> hashMap = new HashMap<>(); // Hashmap with all the letters as keys and the binary search tree as values
    Map<String, Word> list1; 
    ArrayList<Word> word_list = new ArrayList<Word>();

    public Hashing(Map<String,Word> list, ArrayList<Word> word_list){ //contstructor takes in a map of words and their frequencies
        this.word_list = word_list;
        //initializes the hashmap with all the letters in the alphabet
        for(char c = 'a'; c <= 'z'; c++){
            hashMap.put(c, new BST());
        }
        list1 = list;
        //iterates through the map and adds each word to the binary search tree
        for(String s:list.keySet()){
            char i = s.charAt(0);
            hashMap.get(i).insert( list.get(s));
        }
    }

    
    public void writeListEqual(){
        Map<String, Word> list2 = new HashMap<>();
        
        /*for(String s : list1.keySet()){
            if(list1.get(s).getCountPT() == list1.get(s).getCountYT()){
                 list2.put(s, list1.get(s));
            }
        }*/
        for(Word w: word_list){
            if(w.getCountPT() == w.getCountYT()){
                list2.put(w.getWord(), w);
            }
        }
        Map<String, Word> list3 = new TreeMap<>(list2);
        try{
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("resultsListEqual.txt"));
            for(String s : list3.keySet()){
                //String format = "/t/t";
                String i = s + "\t" + "\t" + list3.get(s).getCountPT() + "\n"; 
                bw.write(i);
            }
            bw.close();
            }
        catch(IOException e){
            System.out.println("Error");
        }
            
        
    }
    
    public void writeListDifference(){
        
        String i = "";
        String g = ""; 
        Map<String, Word> list2 = new HashMap<>();
        /*for(String s : list1.keySet()){
            if(list1.get(s).getCountPT() != list1.get(s).getCountYT()){
                 list2.put(s, list1.get(s));
            }
        }*/
        for(Word w: word_list){
            if(w.getCountPT() != w.getCountYT()){
                list2.put(w.getWord(), w);
            }
        }
        Map<String, Word> list3 = new TreeMap<>(list2);
        try{
            
            BufferedWriter bw = new BufferedWriter(new FileWriter("resultsListDiff.txt"));
            
            for(String s : list3.keySet()){
                
                if(list3.get(s).getCountPT() > list3.get(s).getCountYT()){
                    if(list3.get(s).getCountYT() == 0){
                        
                        // g = "+"+(list3.get(s).getCountPT()) + "PT - ZERO";
                        // i = String.format(format, s, g);
                        i = s + "\t" + "\t" + "+"+(list3.get(s).getCountPT()) + " PT - ZERO";
                        bw.write(i + "\n");
                    }
                    else{
                         
                        //g = "+"+(list3.get(s).getCountPT() - list3.get(s).getCountYT()) + "PT";
                        //i = String.format(format, s, g);
                            i = s + "\t" + "\t"+ "+"+(list3.get(s).getCountPT() - list3.get(s).getCountYT()) + " PT";
                        bw.write(i + "\n");
                    }
            }
            else if(list3.get(s).getCountPT() < list3.get(s).getCountYT()){
                if(list3.get(s).getCountPT() == 0){
                    
                    //g = "+" + list3.get(s).getCountYT() + "YT - ZERO";
                     //i = String.format(format, s, g);
                    i = s + "\t" +"\t"+ "+" + list3.get(s).getCountYT() + " YT - ZERO";
                    bw.write(i + "\n");
                }
                else{
                    
                     
                    // g = "+" + (list3.get(s).getCountYT() - list3.get(s).getCountPT()) + "YT";
                     //i = String.format(format, s, g);
                    i = s + "\t" +"\t"+ "+" + (list3.get(s).getCountYT() - list3.get(s).getCountPT()) + " YT";
                    bw.write(i  + "\n");
                }
                
                
                
            }

            
            }
            bw.close();
            
        }
        catch(IOException e){
            System.out.println("Error");
        }
    }
    //Goes through the hashmap of binary trees and writes out the words of equal frequencies to the output file
    public void writeEqual(){
        //Map<String, Word> list2 = new HashMap<>();
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("resultsEqual.txt"));
            for(Character s : hashMap.keySet()){
                BST.Node temp = hashMap.get(s).getRoot();
                ArrayList<Word> words = new ArrayList<>();
                words = hashMap.get(s).inorderwords(temp); 
                for(Word w :words){
                    if(w.getCountPT() == w.getCountYT()){
                        //String format = "/t/t";
                        String i = w.getWord() + "\t" + "\t" + w.getCountPT() + "\n"; 
                        bw.write(i);
                    }
                
                }
                

            }
            bw.close();
    }
    catch(IOException e){
        System.out.println("Error");
    }
}
    //Goes through the hashmap of binary trees and writes out the words of different frequencies to the output file
    public void writeDifference(){
        //very similar to the writelistdiff however the difference is that instead of directly searching I made an arraylist of all the words in order of the tree and then I just go through the arraylist and print out the words that are not equal
        //Map<String, Word> list2 = new HashMap<>();
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("resultsDiff.txt"));
            for(Character s : hashMap.keySet()){
                BST.Node temp = hashMap.get(s).getRoot();
                ArrayList<Word> words = new ArrayList<>();
                words = hashMap.get(s).inorderwords(temp); 
                for(Word w :words){
                    if(w.getCountPT() != w.getCountYT()){
                        //String format = "%-16s%s%n";
                        String i = "";
                        //String g = "";
                        if(w.getCountPT() > w.getCountYT()){
                            if(w.getCountYT() == 0){
                                i = w.getWord() + "\t" + "\t" + "+"+(w.getCountPT()) + " PT - ZERO";
                                bw.write(i + "\n");
                                
                            }
                            else{

                                i = w.getWord() + "\t" + "\t"+ "+"+(w.getCountPT() - w.getCountYT()) + " PT";
                                bw.write(i + "\n");
                                
                            }
                        }
                        else if(w.getCountPT() < w.getCountYT()){
                            if(w.getCountPT() == 0){
                                
                                i = w.getWord() + "\t" + "\t" + "+" + w.getCountYT() + " YT - ZERO";
                                bw.write(i + "\n");
                            }
                            else{
                                i = w.getWord() + "\t" + "\t" + "+" + (w.getCountYT() - w.getCountPT()) + " YT";
                                bw.write(i + "\n");
                            }
                            
                        }
                    }
                }
            }
            bw.close();
        }
        catch(IOException e){
            System.out.println("Error");
        }
        
    
    
}
}

import java.util.Map;
import java.util.ArrayList;

public class Driver {
    public FileIo file = new FileIo();
    Map<String,Word> map = file.wordMap;
    ArrayList<Word> list = file.wordList;
    String fileName = "";
    String fileName2= "";

    public Driver(String fileName, String fileName2) {
        this.fileName = fileName;
        this.fileName2 = fileName2;
        
        
    }
    /*
    public static void main(String[] args) {
        
        FileIo file = new FileIo();
        
        //boolean i = file.openTxtfile("PT1.txt");
        
        
        
        
        file.readTxtfile("YT1.txt");
        file.readTxtfile("PT1.txt"); 
        
        file.writeTxtfile();

        

        Map<String,Word> map = file.wordMap; 
        Hashing hash = new Hashing(map);
        hash.writeListEqual();
       System.out.println( map.get("actually").getCountYT() + " " +map.get("actually").getCountPT()); 
        hash.writeListDifference();
        hash.writeEqual();
        hash.writeDifference();


       //System.out.print( map.get("able").getCountPT());
 
       // for(String key : map.keySet()) {
            //System.out.println(key + " " + map.get(key).getCountYT());
       // }
        
    }
    */
    
    void readFiles(){
        
        boolean i = file.openTxtfile(fileName);
        boolean e  = file.openTxtfile(fileName2);
        if(!(i && e)){
            System.out.println("Error");
        }
        else{
            System.out.println("Success");
        }
}

    void debug(){
        file.readTxtfile(fileName);
        file.readTxtfile(fileName2);
        file.writeTxtfile();
    }
    void createListEqual(){
        
        Hashing hash = new Hashing(map,list);
        hash.writeListEqual();

    }
    void createListDiff(){
        Hashing hash = new Hashing(map,list);
        hash.writeListDifference();
    }
    void createEqual(){
        Hashing hash = new Hashing(map,list);
        hash.writeEqual();
    }
    void createDiff(){
        Hashing hash = new Hashing(map,list);
        hash.writeDifference();
    }
    
}

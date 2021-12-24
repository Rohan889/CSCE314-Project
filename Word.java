public class Word {
    private String word;
    //private variables that count how much that word is found in the different files. 
    private int countPT;
    private int countYT;

    public Word(String word) {
        this.word = word;
        this.countPT = 0;
        this.countYT = 0;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getCountPT() {
        return countPT;
    }

    public void setCountPT(int countPT) {
        this.countPT = countPT;
    }

    public int getCountYT() {
        return countYT;
    }
    
    public void setCountYT(int countYT) {
        this.countYT = countYT;
    }

    public void incrementPT(){
        countPT++;
    }

    public void incrementYT(){
        countYT++;
    }

    public String toString(){
        String newLine = System.getProperty("line.separator");
        
        return "----------".concat(newLine).concat(word)
        .concat(newLine)
        .concat(Integer.toString(countPT))
        .concat(newLine)
        .concat(Integer.toString(countYT));
    }




}
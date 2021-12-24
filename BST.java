import java.util.ArrayList;

public class BST {
    // got from https://www.softwaretestinghelp.com/binary-search-tree-in-java/
    //Is a binary search tree
    class Node { 
        //This is the node object that is used for each node in the binary tree.
        Word word; 
        Node left, right; 
        public Node(Word data){ 
            //Data that is stored inside the node is the left and right node children of the node and the value of that node. 
            word = data; 
            left = right = null; 
        } 
    } 
    // BST root node 
    Node root; 
   // Constructor for BST 
    BST(){ 
        root = null; 
    }
    Node getRoot(){
        return root;
    } 
    // Inserts a node into the tree using the helper function insert_Recursive
    void insert(Word word)  { 
        root = insert_Recursive(root, word); 
    } 
    void inorder() { 
        inorder_Recursive(root); 
    } 
   
    // recursively traverse the BST  
    void inorder_Recursive(Node root) { 
        if (root != null) { 
            inorder_Recursive(root.left); 
            System.out.print(root.word + " "); 
            inorder_Recursive(root.right); 
        } 
    }
    //Recursively traversed through the BST and makes an arraylist in alphabetical order for the character tree that was chosen 
    ArrayList<Word> inorderwords( Node root){
        ArrayList<Word> words = new ArrayList<Word>();
        inorderwords_Recursive(root, words);
        return words;
    }
    //Helper function for inorderwords
    void inorderwords_Recursive(Node root, ArrayList<Word> words){
        if (root != null) { 
            inorderwords_Recursive(root.left, words); //travels left
            words.add(root.word); 
            inorderwords_Recursive(root.right, words); //travels right 
        } 
    }
    
    
    //Helper function for the node insert
    Node insert_Recursive(Node root, Word word) { 
          //If node empty, insert as node
        if (root == null) { 
            root = new Node(word); 
            return root; 
        } 
        //traverse the tree recursively by checking whether the value is smaller or greater than the current node that is being traversed through
        if (word.getWord().compareTo(root.word.getWord())< 0)     // Travels left 
            root.left = insert_Recursive(root.left, word); 
        else if (word.getWord().compareTo(root.word.getWord())> 0)    // Travels right
            root.right = insert_Recursive(root.right, word); 
        
        // Returns node that has been inserted. 
        return root; 
    } 
    // Search function for the word that you want to find
    boolean search(Word word)  { 
        root = search_Recursive(root, word); 
        // Helper function returns doesn't reutnr null that means the word was found. 
        if (root!= null)
            return true;
        
        //Not found
        else
            return false;
    } 
   
    // Helper function for search function. Recursively traverse the tree 
    Node search_Recursive(Node root, Word word)  { 
        // If null or word is found the function will return the node. 
        if (root==null || root.word==word) 
            return root; 
        // Traverse left as the value is less that 
        if (word.getWord().compareTo(root.word.getWord())< 0) 
            return search_Recursive(root.left, word); 
        // Traverse right as the value is right 
        return search_Recursive(root.right, word); 
    } 
}


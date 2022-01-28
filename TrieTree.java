import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TrieTree {
    static class Trie{
        Trie []child=new Trie[26];
        boolean isEnd;
        int frequency;
        Trie(){
            isEnd=false;
            for(int i=0 ;i<26 ;i++)
                child[i]=null;
        }
    }
    ArrayList<String> choose=new ArrayList<>();
    Trie root=new Trie();
    public void insert(String s){
        int index;
        Trie p=root;
        for(int i=0 ;i<s.length() ;i++){
            index=s.charAt(i) -'a';
            if(p.child[index]==null)
                p.child[index]=new Trie();
            p=p.child[index];
        }
        p.isEnd=true;
        p.frequency=0;
    }
    public void printAllWords(Trie root ,char []s ,int index){
        if(root.isEnd) {
            for(int i=0 ;i<index ;i++)
                System.out.print(s[i]);
            System.out.println();
        }
            for (int i = 0; i < 26; i++) {
                if (root.child[i] != null) {
                    s[index] = (char) (i + 'a');
                    printAllWords(root.child[i], s, index + 1);
                }
            }
    }
    public void print3Words(Trie r ,char []s ,int index, char c){
            if (r.isEnd) {
                String h = "";
                for (int i = 0; i < index; i++) {
                    System.out.print(s[i]);
                    h += s[i];
                }
                choose.add(h);
                System.out.println();
            }
            for (int i = 0; i < 26; i++) {
                if(choose.size()==3)
                    break;
                else if (index == 0) {
                    if (root.child[c - 'a'] != null) {
                        s[index] = c;
                        print3Words(root.child[c - 'a'], s, index + 1, c);
                    }
                } else {
                    if (r.child[i] != null) {
                        s[index] = (char) (i + 'a');
                        print3Words(r.child[i], s, index + 1, c);
                    }
                }
            }
            return;
    }
    public String autoComplete(char ch){
        char [] chars=new char[50];
        print3Words(root,chars,0,ch);
        int select=new Scanner(System.in).nextInt();
        String y=choose.get(select-1);
        choose.clear();
        return y;
    }
    public void readFromFile() throws FileNotFoundException {
        File f=new File("C:\\Users\\Admin\\Desktop\\Trie.txt");
        Scanner sc=new Scanner(f);
        while(sc.hasNextLine()){
            insert(sc.nextLine());
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        TrieTree t=new TrieTree();
        char []c=new char[20];
      //  t.readFromFile();
        ArrayList<Integer> d=new ArrayList<>();
        d.add(3);
        System.out.println(d.size());
        System.out.println(d.get(0));


        //  t.readFromFile();

        /*
        char []s =new char[40];
        char []s1=new char[50];
        t.printAllWords(t.root,s,0);
        System.out.println("-----");
        t.print3Words(t.root,s1,0,'a');
        System.out.println("-----");

         */

    }
}

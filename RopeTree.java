import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class RopeTree {
    String []Str =new String[100];
    Queue<Rope> q=new LinkedList<>();
    Queue<Rope> N1=new LinkedList<>();
    Queue<Rope> N2=new LinkedList<>();

     static ArrayList<String> p=new ArrayList<>();
     static ArrayList<Integer> r=new ArrayList<>();

   // String []inputsStr=new String[1000];
    int NumInput=0;
    static class Rope{
        String str;
        int data,number;
        Rope right,left;
        Rope (int data){
            this.data=data;
            right=null;
            left=null;
        }
        //leaf
        Rope(String s){
            this.str=s;
            this.data=s.length();
        }
    }
    Rope []ArrayRoots= new Rope[100];
    int ArrayR=0;
    public void New(String s){
        separateToSpace(s);
        System.out.println(":)");
        CreateRope();
    }
    public void CreateRope(){
        if(NumInput==1)
          ArrayRoots[ArrayR++] = createAloneNode(Str[0]);

       else if(NumInput==2)
           ArrayRoots[ArrayR++] = create2Nodes(Str);

       else if(NumInput==3){
           System.out.println("3");
           int u=Str[0].length()+Str[1].length();
           ArrayRoots[ArrayR++]= create3Nodes(create2Nodes(Str),u,2,Str);
       }
       else{
           NumInput=2*NumInput-1;
           Rope root=createEmptyTree(NumInput);
           CreateMore3Nodes(root,0);
           r.clear();
        }
    }
    public void separateToSpace(String s){
        int m=0,o;
        for(int i=0; i<s.length() ;i++){
            o=i;
            while(i<s.length()-1 && s.charAt(i)!=' ')
                i++;
            if(i<=s.length()) {
                boolean j=false;
                while(s.charAt(i)==' ') {
                    i++;
                    j=true;
                }
                if(i==s.length()-1){
                    if(j) {
                        Str[m++] = s.substring(o, i);
                        NumInput = m;
                    }
                    else {
                        Str[m++] = s.substring(o, i+1);
                        NumInput = m;
                    }
                }
                else {
                    Str[m++] = s.substring(o, i);
                    NumInput = m;
                }
                if(j) {
                    i--;
                }
            }
            else
                break;
        }
        return;
    }
    public Rope create3Nodes(Rope Rb, int u, int index, String []s){
      Rope j=new Rope(s[index]);
      Rope R=new Rope(u);
      R.left=Rb;
      R.right=j;
      return R;
    }
    public Rope create2Nodes(String []s){
        Rope r1=new Rope(s[0]);
        Rope r2=new Rope(s[1]);
        Rope R=new Rope(s[0].length());
        R.left=r1; R.right=r2;
        return R;
    }
    public Rope createAloneNode(String s){
        Rope a=new Rope(s);
        Rope r=new Rope(s.length());
        r.left=a;
        return r;
    }
    public Rope createEmptyTree(int sum){
        Rope root=new Rope(0); root.str="root";
        Rope leftNode=new Rope(0);
        Rope rightNode =new Rope(0);
        root.left=leftNode;   root.right=rightNode;
        sum-=3;  leftNode.str="leaf";    rightNode.str="leaf";
        q.add(leftNode); q.add(rightNode);
        while(sum!=0){
            Rope e=q.peek(); q.poll();
            e.str="null";
            Rope LN=new Rope(0);  LN.str="leaf";
            Rope RN = new Rope(0);
            e.left=LN; sum--;
            if(sum!=0) {
                e.right=RN;
                RN.str="leaf";
                sum--;
            }
            q.add(LN); q.add(RN);
        }
        while (!q.isEmpty())
            q.poll();
        return root;
    }
    public void preorderRope(Rope root){
        if(root!=null){
            if(root.str!=null) {
                System.out.print(root.str);
                p.add(root.str);
            }
            preorderRope(root.left);
            preorderRope(root.right);
        }
    }

    public void CreateMore3Nodes(Rope root,int index){
        if(root!=null) {
            CreateMore3Nodes(root.left, r.size());
            if (root.str.equals("leaf")) {
                root.str = Str[index];
                root.data = Str[index].length();
                r.add(index);
            } else if (root.str.equals("root")) {
                root.data = updateDataNode(root);
                System.out.println(root.data + " ");
                root.str = null;
                ArrayRoots[ArrayR++] = root;
            } else {
                root.str = null;
                root.data = updateDataNode(root);
                System.out.println(root.data + " ");
            }
            CreateMore3Nodes(root.right, r.size());
        }
    }


    public void concat(Rope a,Rope b){
        Rope newRoot=new Rope(0); int i=0;
        newRoot.right=b;
        newRoot.left=a;
        newRoot.data=updateDataNode(newRoot);
        while (ArrayRoots[i]!=null){
            if(ArrayRoots[i]==a)
                ArrayRoots[i]=newRoot;
            if(ArrayRoots[i]==b)
                ArrayRoots[i] = createAloneNode("----");
          i++;
        }
    }
    public int updateDataNode(Rope r){
        r=r.left;
        int u=r.data;
        while(r.right!=null){
            r=r.right;
            u+=r.data;
        }
        return u;
    }
    public void status(){
        int i=0;
        while (ArrayRoots[i]!=null) {
            preorderRope(ArrayRoots[i]);
            System.out.println();
            i++;
        }
    }
    public static void main(String[] args) throws FileNotFoundException {
        String input=new Scanner(System.in).nextLine();
        RopeTree r=new RopeTree();
        while (input.length()!=0) {
            //New
            if (input.equals("new")) {
                input = new Scanner(System.in).nextLine();
                r.New(input);
            }
            //Status
            else if (input.equals("Status")) {
                r.status();
            }
            //index
            else if(input.substring(0,5).equals("index")) {
                r.separateToSpace(input);
                int m=Integer.parseInt(r.Str[1].trim());
                int n=Integer.parseInt(r.Str[2].trim());
            //    System.out.println(r.indexSearch(r.ArrayRoots[m-1],n));
            }
            //concat
            else if(input.substring(0,6).equals("concat")){
                r.separateToSpace(input);
                int m=Integer.parseInt(r.Str[1].trim());
                int n=Integer.parseInt(r.Str[2].trim());
                r.concat(r.ArrayRoots[m-1],r.ArrayRoots[n-1]);
                System.out.println("done");
            }
            else if(input.substring(0,12).equals("autocomplete")){
                char c=input.charAt(13);
                System.out.println(c);
                TrieTree t=new TrieTree();
                t.readFromFile();
                r.New(t.autoComplete(c));
            }
            input=new Scanner(System.in).nextLine();
        }
        System.out.println("oops :-(");
    }
}

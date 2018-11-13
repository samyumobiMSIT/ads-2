import java.util.Scanner;
 public class Solution {
    public static void main(String[] args) {
         Scanner scan = new Scanner(System.in);
         TST<Integer> st = new TST<Integer>();
        String[] words = loadWords();
        //Your code goes here...
        for(int i = 0;i< words.length;i++){
            st.put(words[i], i);
        }
        String req = scan.nextLine();
        //int sum = 0;
        for (String s : st.keysWithPrefix(req)) {
            System.out.println(s);
            //sum +=1;
        }
        //System.out.println(sum);
    }
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
} 
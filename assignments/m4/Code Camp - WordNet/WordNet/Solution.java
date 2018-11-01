import java.util.*;
import java.io.File;
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String synset = sc.nextLine();
        synset = "Files//" + synset;
        String hypernym = sc.nextLine();
        hypernym = "Files//" + hypernym;
        WordNet wn = null;
        while(sc.hasNext()) {
            String option = sc.nextLine();
            switch(option) {
                case "Graph":
                    wn = new WordNet(hypernym, synset);
                    if(wn.multiple_root) {
                        System.out.println("Multiple roots");
                    } else {
                        System.out.println(wn);
                    }
                break;
                case "Queries":
                    wn = new WordNet(hypernym, synset);
                    while(sc.hasNext()) {
                        String[] qu = sc.nextLine().split(" ");
                        if(qu[0].equals("null") || qu[1].equals("null")) {
                            System.out.println("IllegalArgumentException");
                        } else {
                            System.out.print("distance = " + wn.distance(qu[0], qu[1]));
                            System.out.println(", ancestor = " + wn.sap(qu[0], qu[1]));
                        }
                    }
                break;
            }
        }
    }
}
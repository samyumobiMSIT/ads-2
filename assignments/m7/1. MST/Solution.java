import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {

    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        String s;
        StringTokenizer st;
        while (T > 0) {
            if ((s = br.readLine().trim()) != null) {
                st = new StringTokenizer(s);
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                System.out.println(a - 1);
                while (b > 0) {
                    if ((s = br.readLine().trim()) != null) {

                        b--;
                    }
                }

                T--;

            }
        }

    }

}
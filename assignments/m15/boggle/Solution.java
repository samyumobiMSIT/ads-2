/**.
 * { item_description }
 */
import java.util.Scanner;
/**.
 * Class for solution.
 */
public final class Solution {
    /**.
     * Constructs the object.
     */
    private Solution() {
        // empty constructor
    }
    /**.
     * Main method.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        String caseType = sc.nextLine();
        switch (caseType) {
        case "Score":
            String dictionaryName = sc.nextLine();
            In in = new In("/Files/" + dictionaryName);
            String[] dictionary = in.readAllStrings();
            BoggleSolver solver = new BoggleSolver(dictionary);
            String boardName = sc.nextLine();
            BoggleBoard board = new BoggleBoard("/Files/" + boardName);
            int score = 0;
            for (String word : solver.getAllValidWords(board)) {
                score += solver.scoreOf(word);
            }
            System.out.println("Score = " + score);
            break;
        default:
            try {
                dictionaryName = sc.nextLine();
                in = new In("/Files/" + dictionaryName);
                dictionary = in.readAllStrings();
                solver = new BoggleSolver(dictionary);
                board = null;
                score = 0;
                for (String word : solver.getAllValidWords(board)) {
                    score += solver.scoreOf(word);
                }
                System.out.println("Score = " + score);
            } catch (Exception ex) {
                System.out.println("board is null");
            }
            break;
        }
    }
}
/**
 * Class for solution.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        /**
         * empty constructor.
         */
    }
    /**
     * Main function.
     *
     * @param     args  The arguments
     */
    public static void main(final String[] args) {
        String synsets = StdIn.readString();
        String hypernyms = StdIn.readString();
        String line = StdIn.readString();
        try {
        if (line.equals("Graph")) {
            WordNet wordnet = new WordNet(synsets, hypernyms);
        }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (line.equals("Queries")) {
            String[] arr = StdIn.readString().split(" ");
            if (arr[0].equals("null")) {
                System.out.println("IllegalArgumentException");
            }
        }
    }
}
/**.
 * Class for boggle board.
 */
public class BoggleBoard {
    /**.
     * the 16 Boggle dice (1992 version)
     */
    private static final String[] BOGGLE_1992 = {
        "LRYTTE", "VTHRWE", "EGHWNE", "SEOTIS",
        "ANAEEG", "IDSYTT", "OATTOW", "MTOICU",
        "AFPKFS", "XLDERI", "HCPOAS", "ENSIEU",
        "YLDEVR", "ZNRNHL", "NMIQHU", "OBBAOJ"
    };
    /**.
     * the 16 Boggle dice (1983 version)
     */
    private static final String[] BOGGLE_1983 = {
        "AACIOT", "ABILTY", "ABJMOQ", "ACDEMP",
        "ACELRS", "ADENVZ", "AHMORS", "BIFORX",
        "DENOSW", "DKNOTU", "EEFHIY", "EGINTV",
        "EGKLUY", "EHINPS", "ELPSTU", "GILRUW",
    };
    /**.
     * the 25 Boggle Master / Boggle Deluxe dice
     */
    private static final String[] BOGGLE_MASTER = {
        "AAAFRS", "AAEEEE", "AAFIRS", "ADENNN", "AEEEEM",
        "AEEGMU", "AEGMNN", "AFIRSY", "BJKQXZ", "CCNSTW",
        "CEIILT", "CEILPT", "CEIPST", "DDLNOR", "DHHLOR",
        "DHHNOT", "DHLNOR", "EIIITT", "EMOTTT", "ENSSSU",
        "FIPRSY", "GORRVW", "HIPRRY", "NOOTUW", "OOOTTU"
    };
    /**.
     * the 25 Big Boggle dice
     */
    private static final String[] BOGGLE_BIG = {
        "AAAFRS", "AAEEEE", "AAFIRS", "ADENNN", "AEEEEM",
        "AEEGMU", "AEGMNN", "AFIRSY", "BJKQXZ", "CCENST",
        "CEIILT", "CEILPT", "CEIPST", "DDHNOT", "DHHLOR",
        "DHLNOR", "DHLNOR", "EIIITT", "EMOTTT", "ENSSSU",
        "FIPRSY", "GORRVW", "IPRRRY", "NOOTUW", "OOOTTU"
    };
    /**.
     * { var_description }
     */
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**.
     * { var_description }
     */
    private static final double[] FREQUENCIES = {
        0.08167, 0.01492, 0.02782, 0.04253, 0.12703, 0.02228,
        0.02015, 0.06094, 0.06966, 0.00153, 0.00772, 0.04025,
        0.02406, 0.06749, 0.07507, 0.01929, 0.00095, 0.05987,
        0.06327, 0.09056, 0.02758, 0.00978, 0.02360, 0.00150,
        0.01974, 0.00074
    };
    /**.
     * { var_description }
     */
    private final int m;
    /**.
     * { var_description }
     */
    private final int n;
    /**.
     * { var_description }
     */
    private char[][] board;
    /**.
     * Constructs the object.
     */
    public BoggleBoard() {
        /**.
         * { item_description }
         */
        m = 2 + 2;
        /**.
         * { item_description }
         */
        n = 2 + 2;
        StdRandom.shuffle(BOGGLE_1992);
        board = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String letters = BOGGLE_1992[n * i + j];
                int r = StdRandom.uniform(letters.length());
                board[i][j] = letters.charAt(r);
            }
        }
    }
    /**.
     * Constructs the object.
     *
     * @param      filename  The filename
     */
    public BoggleBoard(final String filename) {
        In in = new In(filename);
        m = in.readInt();
        n = in.readInt();
        if (m <= 0) {
            throw new IllegalArgumentException(
                "number of rows must be a positive integer");
        }
        if (n <= 0) {
            throw new IllegalArgumentException(
            "number of columns must be a positive integer");
        }
        board = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                String letter = in.readString().toUpperCase();
                if (letter.equals("QU")) {
                    board[i][j] = 'Q';
                } else if (letter.length() != 1) {
                    throw new IllegalArgumentException(
                        "invalid character: " + letter);
                } else if (!ALPHABET.contains(letter)) {
                    throw new IllegalArgumentException(
                        "invalid character: " + letter);
                } else {
                    board[i][j] = letter.charAt(0);
                }
            }
        }
    }
    /**.
     * Constructs the object.
     *
     * @param      m1     { parameter_description }
     * @param      n1    { parameter_description }
     */
    public BoggleBoard(final int m1, final int n1) {
        this.m = m1;
        this.n = n1;
        if (m <= 0) {
            throw new IllegalArgumentException(
                "number of rows must be a positive integer");
        }
        if (n <= 0) {
            throw new IllegalArgumentException(
                "number of columns must be a positive integer");
        }
        board = new char[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int r = StdRandom.discrete(FREQUENCIES);
                board[i][j] = ALPHABET.charAt(r);
            }
        }
    }
    /**.
     * Constructs the object.
     *
     * @param      a     { parameter_description }
     */
    public BoggleBoard(final char[][] a) {
        this.m = a.length;
        if (m == 0) {
            throw new IllegalArgumentException(
                "number of rows must be a positive integer");
        }
        this.n = a[0].length;
        if (n == 0) {
            throw new IllegalArgumentException(
                "number of columns must be a positive integer");
        }
        board = new char[m][n];
        for (int i = 0; i < m; i++) {
            if (a[i].length != n) {
                throw new IllegalArgumentException(
                    "char[][] array is ragged");
            }
            for (int j = 0; j < n; j++) {
                if (ALPHABET.indexOf(a[i][j]) == -1) {
                    throw new IllegalArgumentException(
                        "invalid character: " + a[i][j]);
                }
                board[i][j] = a[i][j];
            }
        }
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int rows() {
        return m;
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int cols() {
        return n;
    }
    /**.
     * Gets the letter.
     *
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     *
     * @return     The letter.
     */
    public char getLetter(final int i, final int j) {
        return board[i][j];
    }
    /**.
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder(m + " " + n + "\n");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
                if (board[i][j] == 'Q') {
                    sb.append("u ");
                } else {
                    sb.append("  ");
                }
            }
            sb.append("\n");
        }
        return sb.toString().trim();
    }
}


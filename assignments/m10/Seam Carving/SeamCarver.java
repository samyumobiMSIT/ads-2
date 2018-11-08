import java.util.Arrays;


public class SeamCarver {

    private static final double BORDER_PIXEL_ENERGY = 1000d;

    private static final boolean VERTICAL = true, HORIZONTAL = false;


    private Picture pic;

    public SeamCarver(Picture picture){
        if (picture == null){
            throw new IllegalArgumentException("picture is null");
            
        }
        this.pic = new Picture(picture);
    }

    public Picture picture(){
        return new Picture(this.pic);
    }

    public int width(){
        return pic.width();
    }

    public int height(){
        return pic.height();
    }


    public  double energy(int col, int row){
        validatePixel(col, row);

        if (col == 0 || col == width() -1 || row == 0 || row == height() - 1){
            return BORDER_PIXEL_ENERGY;
        }

        double
                rx = pic.get(col + 1, row).getRed() - pic.get(col - 1, row).getRed(),
                gx = pic.get(col + 1, row).getGreen() - pic.get(col - 1, row).getGreen(),
                bx = pic.get(col + 1, row).getBlue() - pic.get(col - 1, row).getBlue(),
                ry = pic.get(col, row + 1).getRed() - pic.get(col, row - 1).getRed(),
                gy = pic.get(col, row + 1).getGreen() - pic.get(col, row - 1).getGreen(),
                by = pic.get(col, row + 1).getBlue() - pic.get(col, row - 1).getBlue();

        return Math.sqrt(rx * rx + gx * gx + bx * bx + ry * ry + gy * gy + by * by);
    }

    public int[] findHorizontalSeam(){
        Pair[][] energies = new Pair[height()][width()];
        for (int i = 0; i < height(); i++){
            energies[i][0] = new Pair(BORDER_PIXEL_ENERGY, -1);
        }
        for (int col = 1; col < width(); col++){
            energies[0][col] = new Pair(BORDER_PIXEL_ENERGY, -1);
            for (int row = 0; row < height(); row++){
                relaxHorizontal(energies, row, col);
            }
        }
        return extractHorizontalSeam(energies);
    }

    public int[] findVerticalSeam(){
        Pair[][] energies = new Pair[height()][width()];
        for (int i = 0; i < width(); i++){
            energies[0][i] = new Pair(BORDER_PIXEL_ENERGY, -1);
        }

        for (int row = 1; row < height(); row++){
            energies[row][0] = new Pair(BORDER_PIXEL_ENERGY, -1);
            for (int col = 0; col < width(); col++){
                relaxVertical(energies, row, col);
            }
        }
        return extractVerticalSeam(energies);
    }


    public void removeHorizontalSeam(int[] seam){
        if (!isValidSeam(seam, HORIZONTAL)){
            throw new IllegalArgumentException("picture is null");
           
        }
        Picture seamedPicture = new Picture(width(), height() - 1);

        for (int col = 0; col < width(); col++){
            int rowBias = 0;
            for (int row = 0; row < height() - 1; row++){
                if (seam[col] == row){
                    rowBias = 1;
                }
                seamedPicture.set(col, row, pic.get(col, row + rowBias));
            }
        }
        this.pic = seamedPicture;
    }

    public void removeVerticalSeam(int[] seam){
        if (!isValidSeam(seam, VERTICAL)){
            throw new IllegalArgumentException("picture is null");
            
        }
        Picture seamedPicture = new Picture(width() - 1, height());
        for(int row = 0; row < height(); row++){
            int colBias = 0;
            for(int col = 0; col < width() - 1; col++){
                if (seam[row] == col){
                    colBias = 1;
                }
                seamedPicture.set(col, row, pic.get(col + colBias, row));
            }
        }
        this.pic = seamedPicture;
    }

    private void validatePixel(int col, int row){
        if (!isValidPixel(col, row)){
            throw new IllegalArgumentException("picture is null");
           
        }
    }

    private boolean isValidPixel(int col, int row){
        return col > -1 && col < width() && row > -1 && row < height();
    }

    private void relaxVertical(Pair[][] energies, int row, int col){
        double myEnergy = energy(col, row);
        Pair[] paths = {
            new Pair( isValidPixel(col - 1, row -1) ? myEnergy + energies[row - 1][col - 1].energy : Double.MAX_VALUE, col - 1),
            new Pair( isValidPixel(col, row - 1) ? myEnergy + energies[row - 1][col].energy : Double.MAX_VALUE, col),
            new Pair( isValidPixel(col + 1, row - 1) ? myEnergy + energies[row - 1][col + 1].energy : Double.MAX_VALUE, col + 1)
        };
        Arrays.sort(paths);
        energies[row][col] = paths[0];
    }


    private void relaxHorizontal(Pair[][] energies, int row, int col){
        double myEnergy = energy(col, row);
        Pair[] paths = {
                new Pair( isValidPixel(col - 1, row - 1) ? myEnergy + energies[row - 1][col - 1].energy : Double.MAX_VALUE, row - 1),
                new Pair( isValidPixel(col - 1, row) ? myEnergy + energies[row][col - 1].energy : Double.MAX_VALUE, row),
                new Pair( isValidPixel(col - 1, row + 1) ? myEnergy + energies[row + 1][col - 1].energy : Double.MAX_VALUE, row + 1)
        };
        Arrays.sort(paths);
        energies[row][col] = paths[0];
    }

    private int[] extractVerticalSeam(Pair[][] energies){
        int[] seam = new int[height()];
        double lowestEnergy = Double.MAX_VALUE;
        int index = -1;
        // find lowest energy
        for (int col = 0; col < width(); col++){
            if (energies[height() - 1][col].energy < lowestEnergy){
                lowestEnergy = energies[height() - 1][col].energy;
                index = col;
            }
        }

        int row = height() - 1;
        while (row > -1){
            seam[row] = index;
            index = energies[row][index].prev;
            row--;
        }
        return seam;
    }

    private int[] extractHorizontalSeam(Pair[][] energies){
        int[] seam = new int[width()];
        double lowestEnergy = Double.MAX_VALUE;
        int index = -1;
        // find lowest energy
        for (int row = 0; row < height(); row++){
            if (energies[row][width() - 1].energy < lowestEnergy){
                lowestEnergy = energies[row][width() - 1].energy;
                index = row;
            }
        }

        int col = width() - 1;
        while (col > -1){
            seam[col] = index;
            index = energies[index][col].prev;
            col--;
        }
        return seam;
    }


    private boolean isValidSeam(int[] seam, boolean vertical){

        if (seam == null){
            return false;
        }

        if ((vertical && seam.length != height()) || (!vertical && seam.length != width())){
            return false;
        }

        for(int i : seam){
            if ((i < 0 ) || (vertical && i >= width()) || (!vertical && i>= height())){
                return false;
            }
        }
        for (int i = 0; i < seam.length - 1; i++){
            if (Math.abs(seam[i] - seam[i + 1]) > 1){
                return false;
            }
        }
        return true;
    }


    private static class Pair implements Comparable<Pair>{
        public final double energy;
        public final int prev;

        public Pair(double energy, int prev) {
            this.energy = energy;
            this.prev = prev;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.energy > o.energy){
                return 1;
            } else if (this.energy < o.energy){
                return -1;
            }
            return 0;
        }
    }

}

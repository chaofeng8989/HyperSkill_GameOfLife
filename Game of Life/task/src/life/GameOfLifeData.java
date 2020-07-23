package life;

import java.io.IOException;
import java.util.Random;

public class GameOfLifeData {
    boolean[][] matrix;
    int n;
    static int[][] directions = {{1,0}, {-1,0}, {0,1}, {0,-1}, {1,1}, {-1,-1}, {1,-1}, {-1,1}};
    int live;
    public GameOfLifeData(int n, Random random) {
        this.n = n;
        matrix = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = random.nextBoolean();
                if (matrix[i][j]) live++;
            }
        }
    }
    public void print() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j]) {
                    System.out.print('O');
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
    public void next() {
        boolean[][] nextMatrix = new boolean[n][n];
        live = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = getNumberOfLiveNeighbors(i, j);
                if (matrix[i][j]) {
                    if (num == 2 || num == 3) {
                        nextMatrix[i][j] = true;
                    } else {
                        nextMatrix[i][j] = false;
                    }
                } else {
                    if (num == 3) {
                        nextMatrix[i][j] = true;
                    } else {
                        nextMatrix[i][j] = false;
                    }
                }
                if (nextMatrix[i][j]) live++;
            }
        }
        this.matrix = nextMatrix;
    }
    private int getNumberOfLiveNeighbors(int i, int j) {
        int num = 0;
        for (int[] d : directions) {
            int x = (i + d[0] + n) % n;
            int y = (j + d[1] + n) % n;
            if (matrix[x][y]) num++;
        }
        return num;
    }
    public void show(int m) {
        for (int i = 0; i < m; i++) {
            System.out.println("Generation #"+(1+i));
            System.out.println("Alive: " + live);
            print();
            next();
            try {
                if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");
            }
            catch (IOException | InterruptedException e) {}
        }

    }
}
package life;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        GameOfLifeData gameOfLifeData = new GameOfLifeData(n, new Random());

    }
}
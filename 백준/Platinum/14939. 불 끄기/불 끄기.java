import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] bulbs = new char[12][12];

        for (int i = 1; i <= 10; i++) {
            char[] line = br.readLine().toCharArray();
            {
                for (int j = 1; j <= 10; j++) {
                    bulbs[i][j] = line[j - 1];
                }
            }
        }

        System.out.println(process(bulbs));
    }

    private static int process(char[][] bulbs) {
        setSide(bulbs);

        int min = Integer.MAX_VALUE;
        int answer = -1;
        for (int i = 0; i <= 1024; i++) {
            int firstCount = 0;
            char[][] copyArray = copyArray(bulbs);
            for (int j = 0; j < 10; j++) {
                if ((i & (1 << j)) > 0) {
                    turnOn(1, j + 1, copyArray);
                    firstCount++;
                }
            }
            int count = bruteForce(copyArray);
            if (count != -1) {
                answer = min = firstCount + Math.min(min, count);
            }
        }
        return answer;
    }

    private static char[][] copyArray(char[][] bulbs) {
        char[][] copyArray = new char[12][12];
        for (int i = 0; i < 12; i++) {
            copyArray[i] = bulbs[i].clone();
        }
        return copyArray;
    }

    private static int bruteForce(char[][] bulbs) {
        int count = 0;
        for (int i = 1; i < 10; i++) {
            for (int j = 1; j <= 10; j++) {
                if (bulbs[i][j] == 'O') {
                    turnOn(i + 1, j, bulbs);
                    count++;
                }
            }
        }

        for (int i = 1; i <= 10; i++) {
            if (bulbs[10][i] == 'O') {
                return -1;
            }
        }

        return count;
    }

    private static void setSide(char[][] bulbs) {
        for (int j = 1; j <= 10; j++) {
            bulbs[0][j] = '#';
        }
        for (int j = 1; j <= 10; j++) {
            bulbs[11][j] = '#';
        }
        for (int j = 0; j <= 11; j++) {
            bulbs[j][0] = '#';
        }
        for (int j = 0; j <= 11; j++) {
            bulbs[j][11] = '#';
        }
    }

    private static void turnOn(int y, int x, char[][] bulbs) {
        reverse(y, x, bulbs);
        reverse(y, x + 1, bulbs);
        reverse(y, x - 1, bulbs);
        reverse(y + 1, x, bulbs);
        reverse(y - 1, x, bulbs);
    }

    private static void reverse(int y, int x, char[][] bulbs) {
        if (bulbs[y][x] == '#') {
            bulbs[y][x] = 'O';
        } else {
            bulbs[y][x] = '#';
        }
    }
}
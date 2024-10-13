import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[] standard = br.readLine().toCharArray();
        char[] target = br.readLine().toCharArray();
        int last = n - 1;

        int answer1 = process(true, n, standard, target, last);
        int answer2 = process(false, n, standard, target, last);

        if (Math.min(answer1, answer2) == -1)
            System.out.println(Math.max(answer1, answer2));
        else
            System.out.println(Math.min(answer1, answer2));
    }

    private static int process(boolean isPressFirstSwitch, int n, char[] standard, char[] target, int last) {
        char[] electricBulbs = standard.clone();
        int answer = 0;

        if (isPressFirstSwitch) {
            changeStatus(0, electricBulbs);
            changeStatus(1, electricBulbs);
            answer++;
        }

        for (int i = 1; i < n; i++) {
            if (electricBulbs[i - 1] != target[i - 1]) {
                pressTheSwitch(i, last, electricBulbs);
                answer++;
            }
        }

        if (Arrays.equals(electricBulbs, target)) {
            return answer;
        }

        return -1;
    }

    private static void pressTheSwitch(int i, int last, char[] electricBulbs) {
        changeStatus(i - 1, electricBulbs);
        changeStatus(i, electricBulbs);
        if (i < last)
            changeStatus(i + 1, electricBulbs);

    }

    private static void changeStatus(int i, char[] electricBulbs) {
        if (electricBulbs[i] == '1')
            electricBulbs[i] = '0';
        else
            electricBulbs[i] = '1';
    }
}
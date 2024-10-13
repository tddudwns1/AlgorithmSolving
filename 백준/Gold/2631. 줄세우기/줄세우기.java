import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] lineUp = new int[n + 1];
        lineUp[0] = Integer.parseInt(br.readLine());
        int index = 0;
        for (int i = 1; i < n; i++) {
            int number = Integer.parseInt(br.readLine());

            if (lineUp[index] < number) {
                lineUp[++index] = number;
            } else {
                int nowIndex = getIndex(lineUp, index, number);

                lineUp[nowIndex] = number;
            }
        }

        System.out.println(n - index - 1);
    }

    private static int getIndex(int[] lineUp, int right, int target) {
        int left = 0;

        while(left < right) {
            int mid = (left + right) / 2;

            if (lineUp[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }
}
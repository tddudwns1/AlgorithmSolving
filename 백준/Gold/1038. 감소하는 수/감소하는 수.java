import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<BigInteger> result = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            for (int[] combination : combinations(10, i)) {
                StringBuilder numBuilder = new StringBuilder();

                for (int digit : combination)
                    numBuilder.append(digit);

                String numStr = numBuilder.reverse().toString();
                result.add(new BigInteger(numStr));
            }
        }

        Collections.sort(result);
        if (n >= result.size())
            System.out.println(-1);
        else
            System.out.println(result.get(n));
    }

    public static List<int[]> combinations(int n, int r) {
        List<int[]> result = new ArrayList<>();
        int[] combination = new int[r];
        generateCombinations(result, combination, 0, 0, n, r);
        return result;
    }

    public static void generateCombinations(List<int[]> result, int[] combination, int index, int start, int n, int r) {
        if (index == r) {
            result.add(combination.clone());
            return;
        }

        for (int i = start; i < n; i++) {
            combination[index] = i;
            generateCombinations(result, combination, index + 1, i + 1, n, r);
        }
    }
}
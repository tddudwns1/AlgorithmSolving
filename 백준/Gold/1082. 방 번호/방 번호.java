import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] prices = new int[n];
        for (int i = 0; i < n; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        int account = Integer.parseInt(br.readLine());

        System.out.println(process(n, prices, account));
    }

    private static String process(int n, int[] prices, int account) {
        String[] counts = new String[account + 1];
        Arrays.fill(counts, "");
        counts[account] = "0";
        for (int priceOfNumber = n - 1; priceOfNumber > 0; priceOfNumber--) {
            for (int nowTotal = prices[priceOfNumber]; nowTotal <= account; nowTotal++) {
                if (counts[nowTotal].length() > counts[nowTotal - prices[priceOfNumber]].length() + 1)
                    continue;

                String newCounts = counts[nowTotal - prices[priceOfNumber]] + priceOfNumber;

                if (counts[nowTotal].compareTo(newCounts) < 0)
                    counts[nowTotal] = newCounts;
                else if (counts[nowTotal].length() < newCounts.length())
                    counts[nowTotal] = newCounts;
            }
        }

        for (int nowTotal = prices[0]; nowTotal <= account; nowTotal++) {
            if (counts[nowTotal - prices[0]].isEmpty())
                continue;

            String newCounts = counts[nowTotal - prices[0]] + 0;

            if (counts[nowTotal].length() > counts[nowTotal - prices[0]].length() + 1)
                continue;
            if (counts[nowTotal].compareTo(newCounts) < 0)
                counts[nowTotal] = newCounts;
            else if (counts[nowTotal].length() < newCounts.length())
                counts[nowTotal] = newCounts;
        }

        return counts[account];
    }
}
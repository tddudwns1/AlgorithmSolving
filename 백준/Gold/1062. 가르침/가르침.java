import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, k, max = 0, target = 0;
    static int[] words;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char[] basic = {'a', 'c', 'i', 'n', 't'};
        int alpha = 0;
        for (int i = 0; i < 5; i++)
            alpha |= 1 << (basic[i] - '`');

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new int[n];

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            words[i] = alpha;
            for (int j = 4; j < word.length() - 4; j++)
                words[i] |= 1 << (word.charAt(j) - '`');
            target |= words[i];
        }

        target ^= alpha;

        System.out.println(checkCanTeach(alpha));
    }

    private static int checkCanTeach(int alpha) {
        if (k < 5)
            return 0;
        if (k == 26)
            return n;
        int count = 0;
        for(int i = 1; i <= 26; i++)
            if ((target & (1 << i)) > 0)
                count++;
        if(count < k - 5)
            return n;
        countCanTeach(2, 5, alpha);
        return max;
    }

    private static void countCanTeach(int index, int count, int key) {
        if (k == count) {
            int total = 0;
            for (int i = 0; i < n; i++)
                if (words[i] == (key & words[i]))
                    total++;
            max = Integer.max(max, total);
            return;
        }

        for (int i = index; i <= 26; i++)
            if ((target & (1 << i)) > 0)
                countCanTeach(i + 1, count + 1, key | (1 << i));
    }
}
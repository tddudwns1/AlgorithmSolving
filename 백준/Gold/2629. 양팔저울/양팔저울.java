import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;

        int weightsCount = Integer.parseInt(br.readLine());
        int[] weights = new int[weightsCount];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < weightsCount; i++)
            sum += weights[i] = Integer.parseInt(st.nextToken());

        boolean[] canCheck = new boolean[sum + 1];
        canCheck[0] = true;

        int beforeMax = 0;

        // 추 무게를 더하는 경우
        for (int weight : weights) {
            beforeMax += weight;
            for (int j = beforeMax - weight; j >= 0; j--)
                if (canCheck[j])
                    canCheck[j + weight] = true;
        }

        // 추 무게를 빼는 경우
        for (int i = 0; i < weightsCount; i++)
            for (int j = weights[i] + 1; j <= beforeMax; j++)
                if (canCheck[j])
                    canCheck[j - weights[i]] = true;

        StringBuilder sb = new StringBuilder();

        int beadsCount = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < beadsCount; i++)
            sb.append(yn(Integer.parseInt(st.nextToken()), canCheck, sum));

        System.out.println(sb);
    }

    private static String yn(int bead, boolean[] canCheck, int sum) {
        if (bead <= sum && canCheck[bead])
            return "Y ";
        return "N ";
    }
}
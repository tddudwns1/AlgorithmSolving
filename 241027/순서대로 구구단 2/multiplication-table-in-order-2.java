import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[2];
        for (int i = 0; i < 2; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Queue<Integer> q = new ArrayDeque<>();
        if (numbers[0] < numbers[1])
            for (int i = numbers[0]; i <= numbers[1]; i++) {
                q.add(i);
            }
        else
            for (int i = numbers[0]; i >= numbers[1]; i--) {
                q.add(i);
            }

        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {
            int index = q.poll();
            for (int i = 1; i <= 9; i++) {
                sb.append(index).append(" * ").append(i).append(" = ").append(index * i).append("  ");
                if (i % 3 == 0)
                    sb.append("\n");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
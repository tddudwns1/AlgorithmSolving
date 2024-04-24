import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();

        Queue<Integer> q = new ArrayDeque<>();
        int num = 0;

        for (int i = 0; i < line.length(); i++) {
            char now = line.charAt(i);

            if (now != '+' && now != '-') {
                sb.append(now);
                continue;
            }

            num += Integer.parseInt(sb.toString());
            sb.setLength(0);

            if (now == '-') {
                q.add(num);
                num = 0;
            }
        }


        num += Integer.parseInt(sb.toString());
        q.add(num);

        int total = q.poll();

        while (!q.isEmpty()) {
            total -= q.poll();
        }

        System.out.println(total);
    }
}
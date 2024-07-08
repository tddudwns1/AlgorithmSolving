import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer>[] players = new PriorityQueue[12];
        for (int i = 1; i <= 11; i++)
            players[i] = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int p = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            players[p].add(w);
        }

        int answer = 0;

        for (int i = 1; i <= 11; i++) {
            if (players[i].isEmpty())
                continue;

            for (int j = 0; j < k; j++) {
                int now = players[i].poll();

                if (now == 0)
                    break;

                players[i].add(now - 1);
            }

            answer += players[i].poll();;
        }

        System.out.println(answer);
    }
}
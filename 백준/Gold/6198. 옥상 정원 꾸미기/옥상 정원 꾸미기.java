import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long count = 0;

        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            int now = Integer.parseInt(br.readLine());

            while (!dq.isEmpty() && dq.peekLast() <= now) {
                dq.removeLast();
                count += dq.size();
            }

            dq.addLast(now);
        }

        for (int i = dq.size() - 1; i >= 1; i--)
            count += i;

        System.out.println(count);
    }
}
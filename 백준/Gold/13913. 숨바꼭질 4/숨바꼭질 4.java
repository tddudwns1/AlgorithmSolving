import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Memory {
    int index;
    int before;

    public Memory(int index, int before) {
        this.index = index;
        this.before = before;
    }
}

public class Main {
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Memory[] field = new Memory[100_001];
        field[n] = new Memory(n, -1);

        bfs(field, n, k);

        StringBuilder sb = new StringBuilder();

        recursion(field, n, k, sb);
        sb.append(k);

        System.out.println(answer);
        System.out.println(sb);
    }

    private static void bfs(Memory[] field, int n, int k) {
        Queue<Integer> position = new ArrayDeque<>();

        position.add(n);

        while (true) {
            int now = position.poll();

            if (now == k)
                return;

            int teleport = now * 2;
            if (teleport <= 100_000 && field[teleport] == null) {
                field[teleport] = new Memory(teleport, now);
                position.add(teleport);
            }

            int front = now + 1;
            if (front <= 100_000 && field[front] == null) {
                field[front] = new Memory(front, now);
                position.add(front);
            }

            int back = now - 1;
            if (back >= 0 && field[back] == null) {
                field[back] = new Memory(back, now);
                position.add(back);
            }
        }
    }

    private static void recursion(Memory[] field, int n, int k, StringBuilder sb) {
        if (k == n)
            return;

        recursion(field, n, field[k].before, sb);
        sb.append(field[k].before).append(" ");
        answer++;
    }
}
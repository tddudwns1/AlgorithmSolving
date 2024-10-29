import java.io.*;
import java.util.*;

public class Main {
    static class Info implements Comparable<Info> {
        int h;
        int i;

        public Info(int h, int i) {
            this.h = h;
            this.i = i;
        }

        @Override
        public int compareTo(Info o) {
            return h - o.h;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<Info> pq = new PriorityQueue<>();
        
        boolean[] checked = new boolean[n + 2];
        checked[0] = true;
        checked[n + 1] = true;

        int answer = 0;
        int parts = 1;

        for (int i = 1; i <= n; i++) {
            pq.add(new Info(Integer.parseInt(br.readLine()), i));
        }

        int lastHeight = 0;
        while (!pq.isEmpty()) {
            if (pq.peek().h != lastHeight){
                answer = Math.max(answer, parts);
                lastHeight = pq.peek().h;
            }
            Info now = pq.poll();

            checked[now.i] = true;

            int prev = now.i - 1;
            int next = now.i + 1;
            if (checked[prev] && checked[next]) {
                parts -= 1;
            } else if (!(checked[prev] || checked[next])) {
                parts += 1;
            }
        }

        System.out.println(answer);
    }
}
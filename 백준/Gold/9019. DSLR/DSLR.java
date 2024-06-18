import java.io.*;
import java.util.*;

class Main {
    static class Register {
        int value;
        String command;
        
        public Register(int value, String command) {
            this.value = value;
            this.command = command;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            sb.append(process(A, B)).append("\n");
        }
        
        System.out.println(sb);
    }

    private static String process(int start, int target) {
        Queue<Register> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[10000];
        queue.add(new Register(start, ""));
        visited[start] = true;

        while (!queue.isEmpty()) {
            Register current = queue.poll();

            if (current.value == target) {
                return current.command;
            }

            int D = (current.value * 2) % 10000;
            if (!visited[D]) {
                queue.add(new Register(D, current.command + "D"));
                visited[D] = true;
            }

            int S = current.value == 0 ? 9999 : current.value - 1;
            if (!visited[S]) {
                queue.add(new Register(S, current.command + "S"));
                visited[S] = true;
            }

            int L = (current.value % 1000) * 10 + current.value / 1000;
            if (!visited[L]) {
                queue.add(new Register(L, current.command + "L"));
                visited[L] = true;
            }

            int R = (current.value % 10) * 1000 + current.value / 10;
            if (!visited[R]) {
                queue.add(new Register(R, current.command + "R"));
                visited[R] = true;
            }
        }

        return "";
    }
}
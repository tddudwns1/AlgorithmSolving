import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < 6; i++) {
            q.add(Integer.parseInt(st.nextToken()));
        }
        
        st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        
        int t = 0;
        for (int now : q) {
            t += now / T;
            t += (now % T == 0 ? 0 : 1);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(t).append("\n").append(N / P).append(" ").append(N % P);
        System.out.println(sb);
    }
}
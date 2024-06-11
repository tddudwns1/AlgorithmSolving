import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        char[][] edge = new char[n][n];
        char[][] canGo = new char[n][n];
        
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                edge[i][j] = st.nextToken().charAt(0);
                canGo[i][j] = '0';
            }
        }
        
        for (int i = 0; i < n; i++) {
            process(i, i, n, edge, canGo, new boolean[n]);
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                sb.append(canGo[i][j]).append(" ");
            sb.append("\n");
        }
        
        System.out.println(sb);
    }
    
    private static void process(int now, int start, int n, char[][] edge, char[][] canGo, boolean[] visited) {
        if (visited[now]) return;
        visited[now] = true;
          
        for (int i = 0; i < n; i++) {
            if (edge[now][i] == '1' && canGo[start][i] == '0') {
                canGo[start][i] = '1';
                process(i, start, n, edge, canGo, visited);
            }
        }
    }
}
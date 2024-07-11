import java.io.*;

class Main {
    static boolean[] checked = new boolean[9*9*9*9*9*9*9*9*9+1];
    static int answer = 0;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        dfs(1, 0, n);
        
        System.out.println(answer);
    }
    
    public static void dfs(int now, int depth, int n) {
        if (depth == n) {
            if (checked[now])
                return;
            checked[now] = true;
            answer++;
            return;
        }
        
        for (int i = 1; i <= 9; i++) {
            dfs(now * i, depth + 1, n);
        }
    }
}
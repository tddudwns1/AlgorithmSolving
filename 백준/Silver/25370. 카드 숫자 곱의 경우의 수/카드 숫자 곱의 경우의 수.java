import java.io.*;
import java.util.*;

class Main {
    static HashSet<Integer> checked = new HashSet<>();
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        dfs(1, 1, 0, n);
        
        System.out.println(checked.size());
    }
    
    public static void dfs(int index, int now, int depth, int n) {
        if (depth == n) {
            checked.add(now);
            return;
        }
        
        for (int i = index; i <= 9; i++) {
            dfs(i, now * i, depth + 1, n);
        }
    }
}
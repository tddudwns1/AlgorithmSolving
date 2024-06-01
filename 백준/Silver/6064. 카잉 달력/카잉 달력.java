import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            sb.append(process(m, n, x, y)).append("\n");
        }
        
        System.out.println(sb);
    }
    
    public static int process(int m, int n, int x, int y) {
        int limit = getLimit(m, n);
        
        int i = 0;
        int big = Math.max(m, n);
        int small = Math.min(m, n);
        int bigTarget = (big == m) ? x : y;
        int smallTarget = (big != m) ? x : y;
        
        if (small == smallTarget)
            smallTarget = 0;
        
        while (true) {
            int standard = i++ * big + bigTarget;
            if (standard > limit)
                return -1;
            
            if (standard % small != smallTarget)
                continue;
            
            return standard;
        }
    }
    
    public static int getLimit(int m, int n) {
        Stack<Integer> stack = new Stack();
        
        int standard = Math.min(Math.min(m, n), (int)Math.sqrt(Math.max(m, n)));
        
        for (int i = standard; i > 1; i--) {
            if (m % i != 0)
                continue;
            
            if (n % i != 0)
                continue;
            
            m /= i;
            n /= i;
            stack.add(i);
        }
                                
        int limit = m * n;
        
        for (int now : stack)
            limit *= now;
            
        return limit;
    }
}
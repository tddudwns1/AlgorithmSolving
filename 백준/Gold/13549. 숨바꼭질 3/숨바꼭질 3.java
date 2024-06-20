import java.io.*;
import java.util.*;

class Main {
    private static class Move{
        int point;
        int time;
        
        public Move(int point, int time) {
            this.point = point;
            this.time = time;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        System.out.println(process(n, k));
    }
    
    private static int process(int n, int k) {
        Queue<Move> q = new ArrayDeque<>();
        boolean[] checked = new boolean[100_001];
        q.add(new Move(n, 0));
        
        while(!q.isEmpty()) {
            Move now = q.poll();
            
            if(checked[now.point])
                continue;
            
            checked[now.point] = true;
            
            if(now.point == k)
                return now.time;
            
            if(now.point <= 50_000)
                q.add(new Move(now.point * 2, now.time));
            
            if(now.point > 0)
                q.add(new Move(now.point - 1, now.time + 1));
            
            if(now.point < 100_000)
                q.add(new Move(now.point + 1, now.time + 1));
        }
        
        return -1;
    }
}
import java.io.*;
import java.util.*;

class Solution {
    static int answer = 0;
    static boolean[] visited;
    
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(visited[i])
                continue;
            bfs(n, computers, i);
            answer++;
        }
        
        return answer;
    }
    
    static void bfs(int n, int[][] computers, int start){
        Queue<Integer> dq = new ArrayDeque();
        dq.add(start);
        visited[start] = true;
        
        while(!dq.isEmpty()){
            int now = dq.poll();
            for(int i = 0; i < n; i++){
                if(visited[i])
                    continue;
                if(computers[now][i] == 0)
                    continue;
                dq.add(i);
                visited[i] = true;
            }
        }
    }
}
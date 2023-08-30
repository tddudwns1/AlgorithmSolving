
import java.util.*;
import java.io.*;

public class Main {
	
	static int[][] map;
	static int[] dp;
	static int n;
	static int min;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visited = new boolean[n];
		min = Integer.MAX_VALUE;
		
		visited[0] =true;
		dfs(0, 0, 1, 0);
		System.out.println(min);
	}
	
	public static void dfs(int pre, int start, int depth, int value) {
		//기저 조건
		// 정점을 모두 돌았다면?
		if(depth == n) {
			if(map[pre][start] == 0) return; // 길이 없음
			value += map[pre][start];
			min = Math.min(min, value);
		}
		
		for(int i = 0; i<n; i++) {
			if(visited[i] || map[pre][i] == 0) continue;
			visited[i] = true;
			dfs(i , start, depth+1, value+map[pre][i]);
			visited[i] = false;
		}
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static char[][] map;
	static int[][] visited;
	static Map<Character, int[]> move;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		move = new HashMap<>();
		move.put('U', new int[] { -1, 0 });
		move.put('D', new int[] { 1, 0 });
		move.put('L', new int[] { 0, -1 });
		move.put('R', new int[] { 0, 1 });

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		visited = new int[n][m];
		for (int i = 0; i < n; i++)
			map[i] = br.readLine().toCharArray();

		int ans = 0;
		int mask = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (visited[i][j] == 0)
					ans += search(i, j, ++mask);
		System.out.println(ans);
	}

	private static int search(int y, int x, int mask) {
		if(visited[y][x] == mask)
			return 1;
		if(visited[y][x] != 0)
			return 0;
		visited[y][x] = mask;
		int dy = y + move.get(map[y][x])[0];
		int dx = x + move.get(map[y][x])[1];
		return search(dy, dx, mask);
	}
}
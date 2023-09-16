import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int ans;
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
		ans = 0;
		int mask = 0;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (visited[i][j] == 0)
					search(i, j, ++mask);
		System.out.println(ans);
	}

	private static void search(int y, int x, int mask) {
		if (visited[y][x] == mask) {
			ans++;
			return;
		}
		if (visited[y][x] != 0)
			return;
		visited[y][x] = mask;
		int dy = y + move.get(map[y][x])[0];
		int dx = x + move.get(map[y][x])[1];
		search(dy, dx, mask);
	}
}
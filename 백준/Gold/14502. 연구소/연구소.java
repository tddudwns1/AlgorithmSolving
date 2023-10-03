import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static Queue<int[]> virus = new ArrayDeque();
	static int[][] move = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static String[][] lab;
	static int n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		lab = new String[n + 2][m + 2];
		int safe = 0;

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++)
				if ("0".equals(lab[i][j] = st.nextToken()))
					safe++;
				else if ("2".equals(lab[i][j]))
					virus.add(new int[] { i, j });
		}

		int ans = 0;
		int end = n * m;
		for (int i = 0; i < end - 2; i++) {
			if(setWall(i))
				continue;
			for (int j = i + 1; j < end - 1; j++) {
				if(setWall(j))
					continue;
				for (int k = j + 1; k < end; k++) {
					if(setWall(k))
						continue;
					ans = Math.max(ans, safe - bfs() - 3);
					getWall(k);
				}
				getWall(j);
			}
			getWall(i);
		}

		System.out.println(ans);
	}

	private static int bfs() {
		boolean[][] visited = new boolean[n + 2][m + 2];
		int cnt = 0;
		Queue<int[]> q = new ArrayDeque<>();
		Iterator<int[]> it = virus.iterator();
		while(it.hasNext())
			q.add(it.next());
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			for(int i = 0; i < 4; i++) {
				int dy = now[0] + move[i][0];
				if(dy < 1 || dy > n)
					continue;
				int dx = now[1] + move[i][1];
				if(dx < 1 || dx > m)
					continue;
				if(visited[dy][dx])
					continue;
				if(lab[dy][dx] == null || !lab[dy][dx].equals("0"))
					continue;
				visited[dy][dx] = true;
				q.add(new int[] {dy, dx});
				cnt++;
			}
		}
		
		return cnt;
	}

	private static boolean setWall(int pos) {
		int y = pos / m + 1, x = pos % m + 1;
		if (!lab[y][x].equals("0"))
			return true;
		lab[y][x] = "1";
		return false;
	}

	private static void getWall(int pos) {
		int y = pos / m + 1, x = pos % m + 1;
		lab[y][x] = "0";
	}
}

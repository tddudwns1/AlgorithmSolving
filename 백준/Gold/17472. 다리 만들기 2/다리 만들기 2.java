import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] move = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[][] map = new int[n + 2][m + 2];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		int cnt = discoveryIsland(map, n, m);

		int[][] distance = new int[cnt + 1][cnt + 1];
		for (int i = 2; i <= cnt; i++)
			for (int j = 2; j <= cnt; j++)
				distance[i][j] = Integer.MAX_VALUE;

		setDistance(map, distance, n, m, cnt);

		System.out.println(prim(distance, cnt));
	}

	private static int prim(int[][] distance, int cnt) {
		boolean[] visited = new boolean[cnt + 1];
		int minWeight[] = new int[cnt + 1];
		for (int i = 2; i <= cnt; i++)
			minWeight[i] = Integer.MAX_VALUE;

		int index = 2;
		minWeight[index] = 0;
		int num = 0;

		while (index != -1) {
			num++;
			visited[index] = true;
			for (int i = 2; i <= cnt; i++)
				if (!visited[i] && distance[index][i] != Integer.MAX_VALUE)
					minWeight[i] = Math.min(minWeight[i], distance[index][i]);
			index = -1;
			for (int i = 2; i <= cnt; i++)
				if (!visited[i] && minWeight[i] != Integer.MAX_VALUE)
					if (index == -1 || minWeight[index] > minWeight[i])
						index = i;
		}

		int sumWeight = 0;
		for (int i = 2; i <= cnt; i++)
			sumWeight += minWeight[i];
		
		if(num != cnt - 1)
			return -1;
		
		return sumWeight;
	}

	private static void setDistance(int[][] map, int[][] distance, int n, int m, int cnt) {
		for (int i = 1; i <= n; i++) {
			int j = 0;
			while (++j <= m && map[i][j] == 0)
				;
			int start = map[i][j];
			int len = 0;
			for (; j <= m; j++) {
				int now = map[i][j];
				if (now == 0) {
					len++;
				} else if (now != start) {
					if (len != 1) {
						distance[start][now] = distance[now][start] = Math.min(len, distance[start][now]);
					}
					start = now;
					len = 0;
				} else {
					len = 0;
				}
			}
		}

		for (int i = 1; i <= m; i++) {
			int j = 0;
			while (++j <= n && map[j][i] == 0)
				;
			int start = map[j][i];
			int len = 0;
			for (; j <= n; j++) {
				int now = map[j][i];
				if (now == 0) {
					len++;
				} else if (now != start) {
					if (len != 1) {
						distance[start][now] = distance[now][start] = Math.min(len, distance[start][now]);
					}
					start = now;
					len = 0;
				} else {
					len = 0;
				}
			}
		}
	}

	private static int discoveryIsland(int[][] map, int n, int m) {
		int num = 1;
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= m; j++)
				if (map[i][j] == 1)
					setNum(map, i, j, ++num, n, m);
		return num;
	}

	private static void setNum(int[][] map, int y, int x, int num, int n, int m) {
		boolean[][] visited = new boolean[n + 2][m + 2];
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { y, x });
		map[y][x] = num;
		visited[y][x] = true;

		while (!q.isEmpty()) {
			int[] now = q.poll();
			for (int i = 0; i < 4; i++) {
				int dy = now[0] + move[i][0];
				int dx = now[1] + move[i][1];
				if (visited[dy][dx])
					continue;
				if (map[dy][dx] != 1)
					continue;
				map[dy][dx] = num;
				visited[dy][dx] = true;
				q.add(new int[] { dy, dx });
			}
		}
	}
}
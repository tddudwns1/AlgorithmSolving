import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Tree implements Comparable<Tree> {
		int y;
		int x;
		int age;

		public Tree(int y, int x, int age) {
			super();
			this.y = y;
			this.x = x;
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return age - o.age;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		int[][] input = new int[n][n];
		int[][] food = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(food[i], 5);
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++)
				input[i][j] = Integer.parseInt(st.nextToken());
		}

		Queue<Tree> trees = new PriorityQueue<>();
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			trees.add(new Tree(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken())));
		}

		Queue<Tree> dead = new ArrayDeque<>();
		int[][] move = { { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 } };

		Queue<Tree> alive = new ArrayDeque<>();
		while (true) {
			while (!trees.isEmpty()) {
				Tree now = trees.poll();
				if (food[now.y][now.x] < now.age)
					dead.add(now);
				else {
					food[now.y][now.x] -= now.age++;
					alive.add(now);
				}
			}

			while (!dead.isEmpty()) {
				Tree now = dead.poll();
				food[now.y][now.x] += now.age / 2;
			}

			while (!alive.isEmpty()) {
				Tree now = alive.poll();
				trees.add(now);
				if (now.age % 5 != 0)
					continue;
				for (int i = 0; i < 8; i++) {
					int dy = now.y + move[i][0];
					if (dy < 0 || dy >= n)
						continue;
					int dx = now.x + move[i][1];
					if (dx < 0 || dx >= n)
						continue;
					trees.add(new Tree(dy, dx, 1));
				}
			}

			if (--k == 0)
				break;

			for (int i = 0; i < n; i++)
				for (int j = 0; j < n; j++)
					food[i][j] += input[i][j];
		}
		System.out.println(trees.size());
	}
}
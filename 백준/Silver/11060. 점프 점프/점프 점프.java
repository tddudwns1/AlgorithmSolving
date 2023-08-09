import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int n;
	static int[] miro;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		miro = new int[n];
		for (int i = 0; i < n; i++) {
			miro[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(bfs());
	}

	public static int bfs() {
		Queue<int[]> q = new LinkedList<>();
		boolean[] visited = new boolean[n];
		q.add(new int[] { 0, 0 });
		visited[0] = true;
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int depth = now[1];
			int nowIndex = now[0];
			if (nowIndex == n - 1) { // 배열의 끝까지 점프했다면 횟수 비교!
				return depth;
			}
			int nowJump = miro[nowIndex];

			// System.out.println(currentIndex + " " + depth);

			if (nowJump == 0) // 아무곳으로도 못 감
				continue;

			for (int i = 1; i <= nowJump; i++) {
				int nextIndex = nowIndex + i;
				if (nextIndex < n) {
					if (!visited[nextIndex]) {
						q.add(new int[] { nextIndex, depth + 1 });
						visited[nextIndex] = true;
					}
				} else // 배열의 범위를 넘으면?
					break;

			}
		}
		return -1;
	}
}

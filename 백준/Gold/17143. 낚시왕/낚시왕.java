import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * array 만들어서 크기만 저장 매번 시간마다 새로운 array 생성 후 priority q에 크기 기준으로 정렬 하나 씩 꺼내면서 위치
 * 저장, 새로운 list에 저장 해당 위치에 상어가 있다면 skip
 */
public class Main {
	static Shark[][] grid;
	static int[][] move = { {}, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	static class Shark implements Comparable<Shark> {
		int r, c, s, d, z;

		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Shark o) {
			return o.z - z;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Queue<Shark> q = new PriorityBlockingQueue<>();
		grid = new Shark[r + 2][c + 2];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int sr = Integer.parseInt(st.nextToken());
			int sc = Integer.parseInt(st.nextToken());
			int ss = Integer.parseInt(st.nextToken());
			int sd = Integer.parseInt(st.nextToken());
			if (sd > 2)
				ss %= (c - 1) * 2;
			else
				ss %= (r - 1) * 2;
			Shark newShark = new Shark(sr, sc, ss, sd, Integer.parseInt(st.nextToken()));
			q.add(newShark);
			grid[sr][sc] = newShark;
		}

		int cnt = 0;
		for (int i = 1; i <= c; i++) {
			for (int j = 1; j <= r; j++)
				if (grid[j][i] != null) {
					q.remove(grid[j][i]);
					cnt += grid[j][i].z;
					grid[j][i] = null;
					break;
				}
			q = move(q, r, c);
		}

		System.out.println(cnt);
	}

	private static Queue<Shark> move(Queue<Shark> q, int r, int c) {
		Queue<Shark> newQ = new PriorityBlockingQueue<>();
		Shark[][] newGrid = new Shark[r + 2][c + 2];

		while (!q.isEmpty()) {
			Shark now = q.poll();
			// 상어이동
			int nowR = now.r; // 세로
			int nowC = now.c; // 가로
			int nowS = now.s; // 속도
			int nowD = now.d; // 방향

			if (nowD > 2) { // 가로
				while (nowS > 0) {
					if (nowD == 3) {
						if (c - nowC >= nowS) {
							nowC += nowS;
							nowS = 0;
							break;
						}
						nowS -= c - nowC;
						nowC = c;
						nowD = 4;
					} else {
						if (nowC > nowS) {
							nowC -= nowS;
							nowS = 0;
							break;
						}
						nowS -= nowC - 1;
						nowC = 1;
						nowD = 3;
					}
				}
			} else {
				while (nowS > 0) {
					if (nowD == 2) {
						if (r - nowR >= nowS) {
							nowR += nowS;
							nowS = 0;
							break;
						}
						nowS -= r - nowR;
						nowR = r;
						nowD = 1;
					} else {
						if (nowR > nowS) {
							nowR -= nowS;
							nowS = 0;
							break;
						}
						nowS -= nowR - 1;
						nowR = 1;
						nowD = 2;
					}
				}
			}

			// 해당 newGrid에 상어가 없다면
			if(newGrid[nowR][nowC] != null)
				continue;

			// newQ에 add, newGrid에 표시
			Shark newShark = new Shark(nowR, nowC, now.s, nowD, now.z);
			newGrid[nowR][nowC] = newShark;
			newQ.add(newShark);
		}

		grid = newGrid;
		return newQ;
	}
}
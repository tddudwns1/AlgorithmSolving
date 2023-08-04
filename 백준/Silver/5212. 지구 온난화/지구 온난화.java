import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	static BufferedReader br;
	static int[][] move; // 섬 주변 탐색할 방향
	static char[][] island; // 현재 섬
	static boolean[][] alive; // 50년 후 섬
	static int[][] edge; // 가장자리 섬의 좌표
	static int n;
	static int m;

	public static void main(String[] args) throws NumberFormatException, IOException {
		init(); // 기본 설정
		makeLand(); // 현재 섬 생성
		scanLand(); // 주변 섬 탐색 후 50년 후 섬 생성
		printLand(); // 가장 자리의 섬의 좌표를 이용하여 50년 후 섬 출력
	}

	private static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		move = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
		island = new char[n + 2][m + 2];
		alive = new boolean[n + 2][m + 2];
		edge = new int[][] { { n + 1, m + 1 }, { -1, -1 } };
	}

	private static void makeLand() throws IOException {
		for (int i = 1; i <= n; i++) {
			char[] chs = br.readLine().toCharArray();
			for (int j = 1; j <= m; j++)
				island[i][j] = chs[j - 1];
		}
	}
	
	private static void scanLand() {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				//해당 좌표가 섬일 때
				if (island[i][j] == 'X') {
					int cnt = 0;
					for (int d = 0; d < 4; d++)
						//섬 주변 탐색 후
						if (island[i + move[d][0]][j + move[d][1]] == 'X')
							cnt++;
					//섬의 수가 2개 이상이면
					if (cnt >= 2) {
						//해당 섬은 생존하는 섬으로 분류
						alive[i][j] = true;
						//가장자리 좌표 업데이트
						edge[0][0] = Math.min(i, edge[0][0]);
						edge[0][1] = Math.min(j, edge[0][1]);
						edge[1][0] = Math.max(i, edge[1][0]);
						edge[1][1] = Math.max(j, edge[1][1]);
						int a = 0;
					}
				}
			}
		}
	}

	private static void printLand() {
		StringBuilder sb = new StringBuilder();
		for (int i = edge[0][0]; i <= edge[1][0]; i++) {
			for (int j = edge[0][1]; j <= edge[1][1]; j++)
				if (alive[i][j])
					sb.append('X');
				else
					sb.append('.');
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int r, c, sheep = 0, wolf = 0;
	static int[] my = {-1, 0, 1, 0}, mx = {0, 1, 0, -1};
	static char[][] backyard;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		backyard = new char[r][c];

		for (int i = 0; i < r; i++)
			backyard[i] = br.readLine().toCharArray();
		
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(include(backyard[i][j]))
					bfs(i, j);
			}
		}
		
		System.out.println(sheep + " " + wolf);
	}
	private static void bfs(int i, int j) {
		Deque<int[]> dq = new ArrayDeque<>();
		int o = 0, v = 0;
		dq.add(new int[] {i, j});
		if(backyard[i][j] == 'o')
			o++;
		else if(backyard[i][j] == 'v')
			v++;
		backyard[i][j] = '#';
		
		while(!dq.isEmpty()) {
			int[] now = dq.poll();
			for(int m = 0; m < 4; m++) {
				int dy = now[0] + my[m];
				if(dy < 0 || dy >= r)
					continue;
				int dx = now[1] + mx[m];
				if(dx < 0 || dx >= c)
					continue;
				if(!include(backyard[dy][dx]))
					continue;
				dq.add(new int[] {dy, dx});
				if(backyard[dy][dx] == 'o')
					o++;
				else if(backyard[dy][dx] == 'v')
					v++;
				backyard[dy][dx] = '#';
			}
		}
		
		if(o > v)
			sheep += o;
		else
			wolf += v;
	}
	private static boolean include(char c) {
		if(c == '.' || c == 'o' || c == 'v')
			return true;
		return false;
	}
}
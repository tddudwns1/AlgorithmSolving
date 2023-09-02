import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] tree = new int[51][3]; // 부모, 누적 리프, 남은 자식 유무
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int root = 0;
		for (int i = 0; i < n; i++) {
			tree[i][0] = Integer.parseInt(st.nextToken());
			tree[i][1] = 1;
			if(tree[i][0] == -1)
				root = i;
		}
		
		for(int i = 0; i < n; i++) {
			int p = tree[i][0];
			if(i == root)
				continue;
			if(tree[p][2]++ == 0)
				continue;
			while(p != -1) {
				tree[p][1]++;
				p = tree[p][0];
			}
		}
		int target = Integer.parseInt(br.readLine());
		int ans = tree[root][1] - tree[target][1];
		if (tree[target][0] != -1 && tree[tree[target][0]][2] == 1)
			ans += 1;
		System.out.println(ans);
	}
}
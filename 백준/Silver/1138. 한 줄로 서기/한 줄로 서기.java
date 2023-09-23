import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] left = new int[n + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++)
			left[i] = Integer.parseInt(st.nextToken());
		
		List<Integer> ans = new ArrayList<>();
		for(int i = n; i > 0; i--)
			ans.add(left[i], i);
		
		StringBuilder sb = new StringBuilder();
		for(int e: ans)
			sb.append(e).append(" ");
		
		System.out.println(sb);
	}
}
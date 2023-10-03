import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());

		List<Integer> lion = new ArrayList<>();
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) 
			if ("1".equals(st.nextToken()))
				lion.add(i);
		
		if(lion.size() < k)
			System.out.println(-1);
		else if (k == 1)
			System.out.println(1);
		else {
			int ans = Integer.MAX_VALUE;
			int range = lion.size() - k + 1;
			for(int i = 0; i < range; i++)
				ans = Math.min(ans, lion.get(i + k - 1) - lion.get(i));
			
			System.out.println(ans + 1);
		}
	}
}

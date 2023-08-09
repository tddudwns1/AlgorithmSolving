import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Set<Integer> papers = new HashSet<>();
		int n = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < n; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			for(int i = 0; i < 10; i++)
				for(int j = 0; j < 10; j++)
					papers.add((String.valueOf(y+i)+" "+String.valueOf(x+j)).hashCode());
		}
		System.out.println(papers.size());
	}
}
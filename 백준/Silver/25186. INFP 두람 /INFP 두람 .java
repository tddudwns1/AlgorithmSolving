import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int max = 0;
		double tot = 0;
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int now = Integer.parseInt(st.nextToken());
			if (max < now)
				max = now;
			tot += now;
		}
		if(tot == 1)
			System.out.println("Happy");
		else if(max > tot / 2)
			System.out.println("Unhappy");
		else
			System.out.println("Happy");
	}
}
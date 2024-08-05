import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int[][] memo;
	static char[] arrA;
	static char[] arrB;

	static void lcs() {

		for (int i = 1; i <= arrA.length; i++) {

			for (int j = 1; j <= arrB.length; j++) {

				if (arrA[i - 1] == arrB[j - 1]) {

					memo[i][j] = memo[i - 1][j - 1] + 1;

				}
				
				else {
					
					memo[i][j] = Math.max(memo[i-1][j],memo[i][j-1]);
					
				}

			}

		}

	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		arrA = br.readLine().toCharArray();
		arrB = br.readLine().toCharArray();
		memo = new int[arrA.length+1][arrB.length+1];

		lcs();

		System.out.println(memo[arrA.length][arrB.length]);
	}

}

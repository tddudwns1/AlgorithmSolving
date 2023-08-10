import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][][] board = new int[n][3][2];
		
		char[] line = br.readLine().toCharArray();
		board[0][0][0] = line[0] - '0';
		board[0][0][1] = line[0] - '0';
		board[0][1][0] = line[2] - '0';
		board[0][1][1] = line[2] - '0';
		board[0][2][0] = line[4] - '0';
		board[0][2][1] = line[4] - '0';
		for(int i = 1; i < n; i++) {
			line = br.readLine().toCharArray();
			board[i][0][0] = Math.max(board[i - 1][0][0], board[i - 1][1][0]) + (line[0] - '0');
			board[i][0][1] = Math.min(board[i - 1][0][1], board[i - 1][1][1]) + (line[0] - '0');
			board[i][1][0] = Math.max(board[i - 1][0][0], Math.max(board[i - 1][1][0], board[i - 1][2][0])) + (line[2] - '0');
			board[i][1][1] = Math.min(board[i - 1][0][1], Math.min(board[i - 1][1][1], board[i - 1][2][1])) + (line[2] - '0');
			board[i][2][0] = Math.max(board[i - 1][1][0], board[i - 1][2][0]) + (line[4] - '0');
			board[i][2][1] = Math.min(board[i - 1][1][1], board[i - 1][2][1]) + (line[4] - '0');
		}
		
		System.out.println(Math.max(board[n - 1][0][0], Math.max(board[n - 1][1][0], board[n - 1][2][0])) + " " + Math.min(board[n - 1][0][1], Math.min(board[n - 1][1][1], board[n - 1][2][1])));
	}
}

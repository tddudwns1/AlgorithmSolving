import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());	// 이번 분기가 몇 분인지 나타내는 정수입니다.
		Stack<int[]> tasks = new Stack<>();			// 이전에 하던 업무 정보를 저장할 스택입니다.
		int score = 0;								// 분기가 끝난 후 업무 평가 점수입니다.
		int[] nowTask = new int[2];					// 현재 작업중인 업무의 정보입니다.
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			if (Integer.parseInt(st.nextToken()) == 1) {	// 만약 받은 지시가 업무일 경우
				tasks.add(nowTask);							// 현재 하던 작업을 멈추고
				nowTask = new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) };
			}												// 새로운 작업을 시작합니다.
			if (--nowTask[1] == 0) {				// 하던 작업의 시간을 줄이고, 만약 남은 시간이 없다면
				score += nowTask[0];				// 업무 평가 점수에 더하고
				nowTask = tasks.pop();				// 이전에 하던 업무를 이어서 합니다.
			}
		}
		System.out.println(score);					// 업무 평가 점수를 출력합니다.
	}
}

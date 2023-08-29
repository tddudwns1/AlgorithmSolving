

import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());


		int[][] rgb = new int[n][3];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				rgb[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 생각해보자
		// 1번과 2번은 달라야함
		// n번과 n-1번은 달라야함
		// i번집의 색은 i -1, i+1 집의 색과 같지 않아야한다.
		// 3가지 경우가 있음 1번 집이 빨강, 초록, 파랑 이렇게 세가지 경우
		// 각경우의 최솟값만 누적하면 답이 나옴


		for (int i = 1; i < n; i++) {
			rgb[i][0] += Math.min(rgb[i-1][1], rgb[i-1][2]);
			rgb[i][1] += Math.min(rgb[i-1][0], rgb[i-1][2]);
			rgb[i][2] += Math.min(rgb[i-1][0], rgb[i-1][1]);
		}
		
		int min = Math.min(Math.min(rgb[n-1][0], rgb[n-1][1]), rgb[n-1][2]);
		System.out.println(min);
	}
}

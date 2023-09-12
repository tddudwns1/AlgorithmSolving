import java.util.*;
import java.io.*;
public class Main {
	static long[] len;
	static long max = 0;
	static int m, n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		len = new long[n];
		for(int i = 0; i<n; i++) {
			len[i] = Integer.parseInt(st.nextToken());
		}
		
		findMax(1, 1000000000);
		
		System.out.println(max);
	}
	
	public static void findMax(long start, long end) {
		
		if(start > end) return;//그만그많ㅎㅎㅎㅎㅎㅎ
		
		long mid = (start + end) / 2;
		
		if(isPossible(mid)) {//나눠 줄 수 있음
			findMax(mid+1, end);
			max = Math.max(max, mid);
		}
		else {//안돼!!
			findMax(start, mid-1);// 값 줄여서 다시 ㄱㄱ
		}
		
	}
	
	public static boolean isPossible(long snack) {
		
		int count = 0;
		for(int i = 0; i<n; i++) {
			count += len[i] / snack;
			if(count >= m)
				return true;
		}
		return false;
	}
}
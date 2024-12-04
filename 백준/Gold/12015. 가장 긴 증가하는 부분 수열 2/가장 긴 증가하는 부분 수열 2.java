import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int[] seq = new int[n];
		for(int i = 0; i < n; i++)
			seq[i] = Integer.parseInt(st.nextToken());
		
		int[] lis = new int[n];
		lis[0] = seq[0];
		int idx = 0;
		for(int i = 1; i < n; i++) {
			if(seq[i] > lis[idx])
				lis[++idx] = seq[i];
			else if(seq[i] < lis[idx]){
				int left = 0, right = idx + 1;
				while(left < right) {
					int mid = (left + right) >> 1;
					if(lis[mid] < seq[i])
						left = mid + 1;
					else
						right = mid;
				}
				lis[left] = seq[i];
			}
		}
		
		System.out.println(idx + 1);
	}
}
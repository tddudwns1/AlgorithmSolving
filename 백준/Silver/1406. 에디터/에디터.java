import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		String[] str = br.readLine().split("");
		int m = Integer.parseInt(br.readLine());
		
		Stack<String> left = new Stack<>();
		
		for(String s:str) {
			left.add(s);
		}
		
		Stack<String> right = new Stack<>();
		
		for(int i = 0; i<m; i++) {
			String[] line = br.readLine().split(" ");
			if(line[0].equals("P")) { // 커서 왼쪽에 문자열을 추가하는 명령어일 경우
				left.add(line[1]);
			}
			else if(line[0].equals("L")) {
				if(!left.isEmpty())
					right.add(left.pop());
			}
			else if(line[0].equals("D")) {
				if(!right.isEmpty())
					left.add(right.pop());
			}
			else if(line[0].equals("B")) {
				if(!left.isEmpty())
					left.pop();
			}
		}
		
		if(!right.isEmpty()) {
			while(!right.isEmpty()) {
				left.add(right.pop());
			}
		}
		
		while(!left.isEmpty()) {
			sb.append(left.pop());
		}
		sb.reverse();
		
		System.out.println(sb);
		
	}
}

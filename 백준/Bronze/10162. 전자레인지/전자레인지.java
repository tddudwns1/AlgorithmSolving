import java.util.*;
import java.io.*;
//베이킹
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(br.readLine());
		int[] time = new int[3]; // 5분, 1분, 10초의 버튼 누른 횟수를 저장하는 배열
		boolean impossible = false; // T초를 맞출 수 없는 경우를 체크하기 위한 변수
		while(t > 0) {
			if(t < 10) { // 10초보다 작은 버튼이 없으므로 T초를 맞출 수 없다.
				impossible = true; // 맞출 수 없음을 표시한다.
				break; // 반복문 종료
			}
			
			//5분 = 5*60 = 300초
			if(t >= 300) { // T가 300초 보다 클 때 
				time[0] = t / 300; // 5분 버튼을 누른 횟수
				t = t % 300; // 누른만큼 초 재설정
			}	
			//1분 = 60초
			else if(t >= 60) { // T가 60초 보다 클 때 
				time[1] = t / 60;
				t = t%60;
			}
			else if(t >= 10) { // T가 10초 보다 클 때 
				time[2] = t / 10;
				t = t%10;
			}
		}
		if(impossible) 
			System.out.println(-1); // T초를 만들 수 없다면 -1 출력
		else
			System.out.println(time[0] + " " + time[1] + " " + time[2]);
	}
}

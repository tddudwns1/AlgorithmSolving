import java.io.*;
import java.util.*;

public class Main {
	static char[] array;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		int l = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
	
		array = new char[c];
		for(int i = 0; i<c; i++) {
			char alphabet = st.nextToken().charAt(0);
			array[i] = alphabet;
		}
		
		Arrays.sort(array);
		
		subSet(0, l, c, 0, "");
	}
	
	public static void subSet(int count, int l, int c, int start, String pwd) {
		if(count == l) { //기저 조건
			int consonant = 0;
			int vowels = 0;
			for(int i = 0; i<l; i++) {
				char alphabet = pwd.charAt(i);
				if(alphabet == 'a' || alphabet == 'e' || alphabet == 'i' || alphabet == 'o' || alphabet == 'u')
					vowels++;
				else
					consonant++;
			}
			
			if(vowels > 0 && consonant > 1)
				System.out.println(pwd);
			return;
		}
		
		for(int i = start; i<c; i++) {
			subSet(count+1, l, c, i+1, pwd+array[i]);
		}
	}
}

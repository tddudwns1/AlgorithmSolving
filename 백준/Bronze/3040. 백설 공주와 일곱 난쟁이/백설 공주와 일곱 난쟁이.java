import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Set<Integer> set = new TreeSet<>();
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			int n = Integer.parseInt(br.readLine());
			sum += n;
			set.add(n);
		}
		sum -= 100;
		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			int n = it.next();
			if(set.contains(sum - n)) {
				set.remove(sum - n);
				set.remove(n);
				break;
			}
		}
		it = set.iterator();
		while (it.hasNext())
			sb.append(it.next()).append("\n");
		System.out.println(sb);
		
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static class Student {
		int front;
		Deque<Integer> backs;

		public Student(int front) {
			super();
			this.front = front;
			this.backs = new ArrayDeque<>();
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		Student[] students = new Student[n + 1];
		for (int i = 1; i <= n; i++)
			students[i] = new Student(i);
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int idx = Integer.parseInt(st.nextToken());
			int target = Integer.parseInt(st.nextToken());

			students[idx].front = target;
			students[target].backs.add(idx);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= n; i++)
			print(i, students, sb);

		System.out.println(sb);
	}

	private static void print(int i, Student[] students, StringBuilder sb) {
		if(students[i].front == 0)
			return;
		while(!students[i].backs.isEmpty()) {
			print(students[i].backs.removeFirst(), students, sb);
		}
//		System.out.println(i);
		sb.append(i).append(" ");
		if(students[i].front != i)
			students[students[i].front].backs.remove(i);
		students[i].front = 0;
	}
}
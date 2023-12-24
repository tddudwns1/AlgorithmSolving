import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static Student[] students;
    static boolean[] checked;

    static class Student {
        Queue<Integer> taller = new ArrayDeque<>();

        public Student() {
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        students = new Student[n + 1];
        for (int i = 1; i <= n; i++)
            students[i] = new Student();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int taller = Integer.parseInt(st.nextToken());
            int shorter = Integer.parseInt(st.nextToken());

            students[shorter].taller.add(taller);
        }

        checked = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (checked[i])
                continue;
            lineUp(i);
        }

        System.out.println(sb);
    }

    private static void lineUp(int now) {
        while (!students[now].taller.isEmpty()) {
            int taller = students[now].taller.poll();
            if (checked[taller])
                continue;
            lineUp(taller);
        }
        if (checked[now])
            return;
        checked[now] = true;
        sb.append(now).append(" ");
    }
}
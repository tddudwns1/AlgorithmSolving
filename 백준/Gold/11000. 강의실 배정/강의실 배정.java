import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Class implements Comparable<Class> {
        long start;
        long end;

        public Class(long start, long end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Class o) {
            return Long.compare(start, o.start);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<Class> classes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());

            classes.add(new Class(start, end));
        }
        Collections.sort(classes);

        Queue<Long> runningClass = new PriorityQueue<>();
        runningClass.add(0L);

        for (Class now : classes) {
            if (now.start >= runningClass.peek())
                runningClass.poll();
            runningClass.add(now.end);
        }

        System.out.println(runningClass.size());
    }
}
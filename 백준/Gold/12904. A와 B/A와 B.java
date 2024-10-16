import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        String t = br.readLine();

        System.out.println(process(s, t));
    }

    private static int process(String s, String t) {
        int answer = 0;
        Queue<String> q = new ArrayDeque<>();

        int turn = t.length() - s.length();

        q.add(t);
        while (true) {
            if (--turn < 0)
                break;
            if (q.isEmpty())
                break;

            String now = q.poll();

            commandA(q, now);
            commandB(q, now);
        }

        for(String now : q) {
            if (now.equals(s)) {
                answer = 1;
                break;
            }
        }

        return answer;
    }

    private static void commandA(Queue<String> q, String now) {
        int lastIndex = now.length() - 1;
        char c = now.charAt(lastIndex);
        if (c != 'A')
            return;

        StringBuilder sb = new StringBuilder(now);

        q.add(sb.deleteCharAt(now.length() - 1).toString());
    }

    private static void commandB(Queue<String> q, String now) {
        int lastIndex = now.length() - 1;
        char c = now.charAt(lastIndex);
        if (c != 'B')
            return;

        StringBuilder sb = new StringBuilder(now);

        q.add(sb.deleteCharAt(now.length() - 1).reverse().toString());
    }
}
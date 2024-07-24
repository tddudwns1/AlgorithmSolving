import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long[] bears = new long[3];
        bears[0] = Long.parseLong(st.nextToken());
        bears[1] = Long.parseLong(st.nextToken());
        bears[2] = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());

        long[] tickets = new long[3];
        tickets[0] = Long.parseLong(st.nextToken());
        tickets[1] = Long.parseLong(st.nextToken());
        tickets[2] = Long.parseLong(st.nextToken());

        long answer = 0;
        int now = -1;

        while (true) {
            now = (now + 1) % 3;

            if (bears[now] < tickets[now]) {
                answer += bears[now];
                tickets[now] -= bears[now];
                bears[now] = 0;
            } else {
                answer += tickets[now];
                bears[now] -= tickets[now];
                tickets[now] = 0;
            }

            if (tickets[0] < 3 && tickets[1] < 3 && tickets[2] < 3)
                break;

            if (bears[0] == 0 && bears[1] == 0 && bears[2] == 0)
                break;

            tickets[(now + 1) % 3] += tickets[now] / 3;
            tickets[now] %= 3;
        }

        System.out.println(answer);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());

        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());
        long z = Long.parseLong(st.nextToken());

        long answer = 0;

        while (true) {
            // a
            if (a != 0)
                if (a < x) {
                    answer += a;
                    x -= a;
                    a = 0;
                } else {
                    answer += x;
                    a -= x;
                    x = 0;
                }

            // b
            if (b != 0)
                if (b < y) {
                    answer += b;
                    y -= b;
                    b = 0;
                } else {
                    answer += y;
                    b -= y;
                    y = 0;
                }

            // c
            if (c != 0)
                if (c < z) {
                    answer += c;
                    z -= c;
                    c = 0;
                } else {
                    answer += z;
                    c -= z;
                    z = 0;
                }

            if (x < 3 && y < 3 && z < 3)
                break;

            if (x >= y && x >= z) {
                y += x / 3;
                x %= 3;
            } else if (y >= x && y >= z) {
                z += y / 3;
                y %= 3;
            } else {
                x += z / 3;
                z %= 3;
            }
        }

        System.out.println(answer);
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * union find 문제
 */
public class Main {
    static class Sample {
        int parent;
        long diffFromRoot = 0;

        public Sample(int parent) {
            this.parent = parent;
        }
    }

    final static String UNKNOWN = "UNKNOWN\n";
    static Sample[] samples;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            if (n == 0)
                break;

            int m = Integer.parseInt(st.nextToken());

            samples = new Sample[n + 1];
            for (int i = 1; i <= n; i++)
                samples[i] = new Sample(i);

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());

                String signal = st.nextToken();
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (signal.equals("!")) {
                    int w = Integer.parseInt(st.nextToken());
                    union(a, b, w);
                } else {
                    if (find(a) == find(b))
                        sb.append(samples[b].diffFromRoot - samples[a].diffFromRoot).append("\n");
                    else
                        sb.append(UNKNOWN);
                }
            }
        }

        System.out.println(sb);
    }

    static int find(int n) {
        if (n == samples[n].parent)
            return n;

        int root = find(samples[n].parent);
        samples[n].diffFromRoot += samples[samples[n].parent].diffFromRoot;

        return samples[n].parent = root;
    }

    static void union(int a, int b, int w) {
        int newA = find(a);
        int newB = find(b);

        if (newA != newB) {
            samples[newB].parent = newA;
            samples[newB].diffFromRoot = samples[a].diffFromRoot - samples[b].diffFromRoot + w;
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int countAlone;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = countAlone = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] leaders = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                leaders[i] = i;
            }
            int[] selects = new int[n + 1];

            for (int i = 1; i <= n; i++) {
                int select = Integer.parseInt(st.nextToken());

                union(i, select, leaders, selects);
            }

            sb.append(countAlone).append("\n");
        }

        System.out.println(sb);
    }

    private static int find(int n, int[] leaders) {
        if (n == leaders[n]) {
            return n;
        }

        return leaders[n] = find(leaders[n], leaders);
    }

    private static void union(int me, int select, int[] leaders, int[] selects) {
        if (me == select) {
            countAlone--;
            return;
        }

        int leader = find(select, leaders);

        if (me == leader) {
            countAlone -= setTeam(select, selects);
        } else {
            leaders[me] = leader;
            selects[me] = select;
        }
    }

    private static int setTeam(int n, int[] parents) {
        if (n == parents[n]) {
            return 0;
        }

        return 1 + setTeam(parents[n], parents);
    }
}
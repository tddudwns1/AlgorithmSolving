import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c, d, clearArea = 0;
    static int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static String[][] room;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        room = new String[n][m];

        st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                room[i][j] = st.nextToken();
        }
        clear();
        System.out.println(clearArea);
    }

    private static void clear() {
        if (room[r][c].equals("0")) {
            room[r][c] = "2";
            clearArea++;
        }
        while (true) {
            int turn = checkAround();
            if (turn == -1) {
                r -= move[d][0];
                c -= move[d][1];
                if (room[r][c].equals("1"))
                    break;
            } else {
                r += move[turn][0];
                c += move[turn][1];
                if (room[r][c].equals("0")) {
                    room[r][c] = "2";
                    clearArea++;
                }
            }
        }
    }

    private static int checkAround() {
        for (int i = 1; i <= 4; i++) {
            int now = d - i >= 0 ? d - i : d - i + 4;
            if (room[r + move[now][0]][c + move[now][1]].equals("0"))
                return d = now;
        }
        return -1;
    }
}

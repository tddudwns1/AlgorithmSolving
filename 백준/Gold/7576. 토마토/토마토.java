import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        String[][] box = new String[n + 2][m + 2];
        int numberOf0 = 0;
        Queue<int[]> positionOfTomatos = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                String now = st.nextToken();
                if ((box[i][j] = now).equals("1"))
                    positionOfTomatos.add(new int[]{i, j});
                else if ((box[i][j] = now).equals("0"))
                    numberOf0++;
            }
        }

        System.out.println(bfs(box, numberOf0, positionOfTomatos));
    }

    private static int bfs(String[][] box, int numberOf0, Queue<int[]> positionOfTomatos) {
        if (positionOfTomatos.isEmpty())
            return -1;

        int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int turn = 0;

        while (!positionOfTomatos.isEmpty()) {
            if (numberOf0 == 0)
                break;

            Queue<int[]> newPositionOfTomatos = new ArrayDeque<>();
            while (!positionOfTomatos.isEmpty()) {
                int[] now = positionOfTomatos.poll();
                for (int i = 0; i < 4; i++) {
                    int dy = now[0] + move[i][0];
                    int dx = now[1] + move[i][1];

                    if (box[dy][dx] == null)
                        continue;

                    if (!box[dy][dx].equals("0"))
                        continue;

                    box[dy][dx] = "1";
                    numberOf0--;
                    newPositionOfTomatos.add(new int[]{dy, dx});
                }
            }

            if (newPositionOfTomatos.isEmpty())
                return -1;

            positionOfTomatos = newPositionOfTomatos;
            turn++;
        }

        return turn;
    }
}

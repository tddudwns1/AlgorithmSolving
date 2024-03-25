import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb=new StringBuilder();

        char[][] map = new char[n][n];

        for (int i = 0; i < n; i++) {

            map[i] = br.readLine().toCharArray();
        }

        List<Integer> list=new ArrayList<>();

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (map[i][j] == '1') {

                    list.add(bfs(map, i, j, n));
                }
            }
        }

        Collections.sort(list);
        sb.append(list.size()).append("\n");
        for(int i = 0; i < list.size(); i++){
            
            sb.append(list.get(i)).append("\n");
        }
        System.out.print(sb);
    }

    private static int bfs(char[][] map, int y, int x, int n) {
        int[][] move = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Queue<int[]> q = new ArrayDeque<>();
        int count = 1;

        q.add(new int[]{y, x});
        map[y][x] = '0';

        while (!q.isEmpty()) {

            int[] now = q.poll();

            for (int i = 0; i < 4; i++) {

                int dy = now[0] + move[i][0];
                if (dy < 0 || dy >= n) {

                    continue;
                }

                int dx = now[1] + move[i][1];
                if (dx < 0 || dx >= n) {

                    continue;
                }

                if (map[dy][dx] == '0') {

                    continue;
                }

                q.add(new int[]{dy, dx});
                map[dy][dx] = '0';
                count++;
            }
        }

        return count;
    }
}
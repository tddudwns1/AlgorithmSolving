import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    enum Status {
        N, S2, S1, O;  // N = 평시, S2 = 2턴 기절, S1 = 1턴 기절, O = 탈락
    }

    static abstract class XmasCharacter {
        int r;
        int c;
    }

    static class Santa extends XmasCharacter implements Comparable<Santa> {
        int num;
        int power;
        int score = 0; // 점수
        Status status = Status.N;

        int distance;

        public Santa(int num, int power) {
            this.num = num;
            this.power = power;
        }

        public void update(Rudolf rudolf, int r, int c) {
            this.r = r;
            this.c = c;
            this.distance = (int) (Math.pow(rudolf.r - r, 2) + Math.pow(rudolf.c - c, 2));
        }

        @Override
        public int compareTo(Santa o) {
            if (distance != o.distance)
                return distance - o.distance;
            if (r != o.r)
                return o.r - r;
            return o.c - c;
        }
    }

    static class Rudolf extends XmasCharacter {
        int power;
        int countOfSantasOut = 0;

        public Rudolf(int r, int c, int power) {
            this.r = r;
            this.c = c;
            this.power = power;
        }
    }

    static class CandidateSanta extends XmasCharacter {
        int distance;
        int[] move;

        public CandidateSanta(int r, int c, int distance, int[] move) {
            this.r = r;
            this.c = c;
            this.distance = distance;
            this.move = move;
        }
    }

    public static void main(String[] args) throws IOException {
        // 여기에 코드를 작성해주세요.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 게임판 크기
        int m = Integer.parseInt(st.nextToken()); // 게임 턴 수
        int p = Integer.parseInt(st.nextToken()); // 산타 수
        int c = Integer.parseInt(st.nextToken()); // 루돌프 힘
        int d = Integer.parseInt(st.nextToken()); // 산타 힘

        XmasCharacter[][] board = new XmasCharacter[n + 1][n + 1];

        st = new StringTokenizer(br.readLine());
        int rr = Integer.parseInt(st.nextToken()); // 루돌프 y
        int rc = Integer.parseInt(st.nextToken()); // 루돌프 x
        Rudolf rudolf = new Rudolf(rr, rc, c);
        board[rudolf.r][rudolf.c] = rudolf;

        Santa[] santas = new Santa[p];
        List<Santa> list = new ArrayList<>();
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()) - 1; // 산타 번호
            int sr = Integer.parseInt(st.nextToken()); // 산타 y
            int sc = Integer.parseInt(st.nextToken()); // 산타 x

            santas[num] = new Santa(num, d);
            santas[num].update(rudolf, sr, sc);
            board[sr][sc] = santas[num];
            list.add(santas[num]);
        }

        // 게임 진행
        // M 개의 턴
        // 루돌프가 한 번 움직인 뒤, 1번 산타부터 P번 산타까지 순서대로
        for (int i = 0; i < m; i++) {
            // 거리 = r1 - r2 제곱 + c1 - c2 제곱
            rudolfTurn(rudolf, list, board, n);

            santaTurn(rudolf, list, santas, board, n);

            if (rudolf.countOfSantasOut == p)
                break;

            reduceStunCount(santas);
            plus1PointToAllNormalSanta(santas);
        }

        printAnswer(santas);
    }

    // 가장 가까운 산타를 향해 1칸 돌진
    // 게임에서 탈락하지 않은 산타 중 가장 가까운 산타
    // 가장 가까운 산타가 2명 이상 -> r 좌표가 큰 산타, c 좌표가 큰 산타
    // 상하좌우, 대각선을 포함한 인접한 8방향
    static void rudolfTurn(Rudolf rudolf, List<Santa> list, XmasCharacter[][] board, int n) {
        Collections.sort(list);

        Santa santa = list.get(0);

        board[rudolf.r][rudolf.c] = null;
        int[][] move = {{0, 0}, {0, -1}, {0, 1}, {-1, 0}, {-1, -1}, {-1, 1}, {1, 0}, {1, -1}, {1, 1}};
        int moveCommand = getMoveCommand(rudolf, santa);
        rudolf.r += move[moveCommand][0];
        rudolf.c += move[moveCommand][1];

        if (board[rudolf.r][rudolf.c] instanceof Santa) {
            // 충돌
            crash(rudolf, santa, rudolf.power, move[moveCommand], n, board, list);
        }

        board[rudolf.r][rudolf.c] = rudolf;
    }

    // 1번부터 P번까지 순서대로
    // 기절했거나 이미 게임에서 탈락한 산타는 움직일 수 없음
    // 루돌프에게 거리가 가장 가까워지는 방향으로 1칸
    // 상우하좌 우선순위
    static void santaTurn(Rudolf rudolf, List<Santa> list, Santa[] santas, XmasCharacter[][] board, int n) {
        int[][] moveSanta = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int[][] knockBackSanta = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        list.clear();

        for (Santa santa : santas) {
            if (santa.status == Status.O)
                continue;

            list.add(santa);

            if (santa.status != Status.N) {
                santa.update(rudolf, santa.r, santa.c);
                continue;
            }

            int diffR = rudolf.r - santa.r; // 음수면 위, 양수면 밑
            int diffC = rudolf.c - santa.c; // 양수면 우, 음수면 좌

            Queue<CandidateSanta> candidates = new ArrayDeque<>();
            for (int i = 0; i < 4; i++) {
                if (diffC == 0 && moveSanta[i][0] == 0)
                    continue;
                if (diffR == 0 && moveSanta[i][1] == 0)
                    continue;
                if (diffR * moveSanta[i][0] < 0)
                    continue;
                if (diffC * moveSanta[i][1] < 0)
                    continue;

                int candidateR = santa.r + moveSanta[i][0];
                int candidateC = santa.c + moveSanta[i][1];

                if (board[candidateR][candidateC] instanceof Santa)
                    continue;

                candidates.add(new CandidateSanta(candidateR, candidateC, (int) (Math.pow(rudolf.r - candidateR, 2) + Math.pow(rudolf.c - candidateC, 2)), knockBackSanta[i]));
            }

            if (candidates.isEmpty()) {
                santa.update(rudolf, santa.r, santa.c);
                continue;
            }

            CandidateSanta candidate = candidates.poll();
            while (!candidates.isEmpty()) {
                CandidateSanta now = candidates.poll();
                if (now.distance < candidate.distance)
                    candidate = now;
            }

            board[santa.r][santa.c] = null;
            if (board[candidate.r][candidate.c] == null) {
                board[candidate.r][candidate.c] = santa;
                santa.update(rudolf, candidate.r, candidate.c);
            } else {
                santa.r -= candidate.move[0];
                santa.c -= candidate.move[1];
                // 충돌
                crash(rudolf, santa, santa.power, candidate.move, n, board, list);
            }
        }
    }

    private static void crash(Rudolf rudolf, Santa santa, int power, int[] move, int n, XmasCharacter[][] board, List<Santa> list) {
        santa.r += power * move[0];
        santa.c += power * move[1];
        santa.score += power;
        santa.status = Status.S2;

        chainReaction(rudolf, santa, move, n, board, list);
    }

    private static void chainReaction(Rudolf rudolf, Santa santa, int[] move, int n, XmasCharacter[][] board, List<Santa> list) {
        if (santa.r < 1 || santa.r > n || santa.c < 1 || santa.c > n) {
            santa.status = Status.O;
            list.remove(santa);
            rudolf.countOfSantasOut++;
            return;
        }

        if (board[santa.r][santa.c] instanceof Santa) {
            Santa next = (Santa) board[santa.r][santa.c];
            next.r += move[0];
            next.c += move[1];
            chainReaction(rudolf, next, move, n, board, list);
        }

        board[santa.r][santa.c] = santa;
        santa.update(rudolf, santa.r, santa.c);
    }

    private static int getMoveCommand(Rudolf rudolf, Santa santa) {
        int moveCommand = 0;
        if (santa.r != rudolf.r) {
            if (santa.r < rudolf.r)
                moveCommand += 3;
            else
                moveCommand += 6;
        }

        if (santa.c != rudolf.c) {
            if (santa.c < rudolf.c)
                moveCommand += 1;
            else
                moveCommand += 2;
        }
        return moveCommand;
    }

    private static void reduceStunCount(Santa[] santas) {
        for (Santa now : santas) {
            if (now.status == Status.S2)
                now.status = Status.S1;
            else if (now.status == Status.S1)
                now.status = Status.N;
        }
    }

    private static void plus1PointToAllNormalSanta(Santa[] santas) {
        for (Santa now : santas)
            if (now.status != Status.O)
                now.score++;
    }

    private static void printAnswer(Santa[] santas) {
        StringBuilder sb = new StringBuilder();
        for (Santa now : santas)
            sb.append(now.score).append(" ");

        System.out.println(sb);
    }
}
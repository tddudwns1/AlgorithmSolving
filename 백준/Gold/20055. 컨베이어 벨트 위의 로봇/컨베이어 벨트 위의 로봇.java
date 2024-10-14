import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Robot {
        int position;

        public Robot(int position) {
            this.position = position;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 벨트의 절반
        int k = Integer.parseInt(st.nextToken()); // 내구도 0 기준 개수

        int beltLength = 2 * n;
        int[] belt = new int[beltLength];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < beltLength; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(process(n, k, belt, beltLength));
    }

    private static int process(int n, int k, int[] belt, int beltLength) {
        Queue<Robot> positionOfRobot = new ArrayDeque<>();
        int stage = 0;

        int doubleOfBelt = beltLength - 1;
        int cursor = 0;

        while (k > 0) {
            stage++;

            // 벨트 회전
            cursor = rotateBelt(doubleOfBelt, cursor);

            int endOfBelt = (n + cursor - 1) % beltLength;
            removeTheRobotAtTheEnd(positionOfRobot, endOfBelt);

            // 로봇 이동 가능하면 이동 (가장 먼저 벨트에 올라간 로봇부터)
            // 로봇 없어야함, 내구도 있어야함
            k -= moveRobot(doubleOfBelt, cursor, n, belt, positionOfRobot);
            removeTheRobotAtTheEnd(positionOfRobot, endOfBelt);

            // 내구도 있으면 로봇 올림
            k -= putRobot(cursor, belt, positionOfRobot);
        }

        return stage;
    }

    private static int rotateBelt(int doubleOfBelt, int cursor) {
        int next = cursor - 1;

        if (next < 0)
            next = doubleOfBelt;

        return next;
    }

    private static int moveRobot(int doubleOfBelt, int cursor, int n, int[] belt, Queue<Robot> positionOfRobot) {
        if (positionOfRobot.isEmpty())
            return 0;

        int zeroCount = 0;

        int prevRobot = -1;
        for (Robot now : positionOfRobot) {
            int next = move(doubleOfBelt, now.position);
            if (belt[next] == 0) {
                prevRobot = now.position;
                continue;
            }

            if (prevRobot == next) {
                prevRobot = now.position;
                continue;
            }

            if (--belt[next] == 0)
                zeroCount++;

            prevRobot = now.position = next;
        }

        return zeroCount;
    }

    private static int move(int doubleOfBelt, int position) {
        if (position == doubleOfBelt)
            position = 0;
        else
            position++;

        return position;
    }

    private static int putRobot(int cursor, int[] belt, Queue<Robot> positionOfRobot) {
        if (belt[cursor] == 0)
            return 0;

        positionOfRobot.add(new Robot(cursor));
        if (--belt[cursor] == 0)
            return 1;
        return 0;
    }

    private static void removeTheRobotAtTheEnd(Queue<Robot> positionOfRobot, int endOfBelt) {
        if (positionOfRobot.isEmpty())
            return;

        if (positionOfRobot.peek().position == endOfBelt)
            positionOfRobot.poll();
    }
}
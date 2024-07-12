import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

class Point {
    int x;
    int y;

    public Point(int y, int x) {
        this.x = x;
        this.y = y;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[][] field = new char[12][6];

        // 뿌요뿌요 필드
        for (int i = 0; i < 12; i++)
            field[i] = br.readLine().toCharArray();

        // 정답으로 출력 할 연쇄 카운팅
        int answer = 0;

        // 4개 이상이 모여 터지게 되면
        while (remove(field)) {
            // 중력의 영향을 받아 아래로 떨어지며
            replace(field);
            // 연쇄가 올라간다
            answer++;
        }

        System.out.println(answer);
    }

    /**
     * 뿌요를 제거할 지 탐색하는 함수
     *
     * @param field 뿌요 필드
     * @return 같은 색 뿌요들이 4개 이상 모여 터졌는지 여부
     */
    private static boolean remove(char[][] field) {
        // 제거 과정에서 중복 탐색을 방지하기 위한 배열
        boolean[][] checked = new boolean[12][6];
        // 터진 그룹이 있는지 여부, 없다면 연쇄 종료를 확인하기 위함
        boolean isRemove = false;

        for (int i = 0; i < 12; i++)
            for (int j = 0; j < 6; j++) {
                // 해당 좌표가 빈 공간이면 다음 좌표 탐색
                if (field[i][j] == '.')
                    continue;

                // 해당 좌표가 탐색한 공간이면 다음 좌표 탐색
                if (checked[i][j])
                    continue;

                // 4개 이상이 모인지 확인 후 제거하고 터진 그룹 여부 활성화
                if (bfs(field, i, j, checked))
                    isRemove = true;
            }

        return isRemove;
    }

    /**
     * 뿌요가 제거되는지 판단하고 제거하는 함수
     * <p>
     * 1. 유효한 좌표에 대해 주변을 탐색
     * 2. 해당 뿌요와 같은 뿌요가 4개 이상 그룹을 지었는지 확인
     * 3. 4개 이상이라면 제거
     * 4. 제거 여부 반환
     *
     * @param field   뿌요필드
     * @param y       y 좌표
     * @param x       x 좌표
     * @param checked 탐색 여부 확인 배열
     * @return
     */
    private static boolean bfs(char[][] field, int y, int x, boolean[][] checked) {
        int[][] move = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        // 탐색을 위한 큐
        Queue<Point> q = new ArrayDeque<>();
        // 제거를 위한 후보 큐
        Queue<Point> candidate = new ArrayDeque<>();
        // 유효한 좌표의 뿌요 색깔
        char target = field[y][x];
        // 이번 연쇄 과정에서 제거가 됐는지 확인하는 변수
        boolean isRemove = false;

        // 해당 좌표 기점으로 시작
        checked[y][x] = true;
        q.add(new Point(y, x));
        candidate.add(new Point(y, x));

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int i = 0; i < 4; i++) {
                // 좌표가 유효한지 탐색 후
                int dy = now.y + move[i][0];
                if (dy < 0 || dy >= 12)
                    continue;

                int dx = now.x + move[i][1];
                if (dx < 0 || dx >= 6)
                    continue;

                // 방문 여부와
                if (checked[dy][dx])
                    continue;

                // 목표 뿌요 여부를 확인
                if (field[dy][dx] != target)
                    continue;

                checked[dy][dx] = true;
                q.add(new Point(dy, dx));
                candidate.add(new Point(dy, dx));
            }
        }

        // 만약 후보 큐 내부 원소 수가 4개 이상이면 제거 진행 후, 제거 여부 활성화
        if (candidate.size() >= 4) {
            for (Point now : candidate)
                field[now.y][now.x] = '.';
            isRemove = true;
        }

        return isRemove;
    }

    /**
     * 제거가 진행 된 후 뿌요 재배치 진행하는 함수
     * <p>
     * 1. 한 세로줄을 기준으로 위에서부터 빈칸을 센다
     * 2. 해당 값을 큐에 순서대로 삽입 후
     * 3. 아래에서부터 큐에서 제거한 빈칸의 수칸큼 위에서 내린다
     *
     * @param field 뿌요필드
     */
    private static void replace(char[][] field) {
        for (int x = 0; x < 6; x++) {
            // 천장에서 부터 센 빈칸의 길이 목록을 저장할 큐
            Queue<Integer> countEmpties = new ArrayDeque<>();

            // 천장에서 부터 센 빈칸의 길이
            int count = 0;

            // 천장까지 뿌요가 가득찼다면 범위 이탈 방지용 수를 넣어준다
            if (field[0][x] != '.')
                countEmpties.add(0);

            for (int y = 0; y < 12; y++) {
                // 빈칸의 수를 센다
                if (field[y][x] == '.') {
                    count++;
                    continue;
                }

                // 빈칸의 길이가 존재하면 큐에 삽입
                if (count != 0)
                    countEmpties.add(count);

                // 빈칸 길이 초기화
                count = 0;
            }

            // 빈칸의 길이가 존재하면 큐에 삽입
            if (count != 0)
                countEmpties.add(count);

            // 천장에서부터 센 의미없는 길이 제거
            if (!countEmpties.isEmpty())
                countEmpties.poll();

            // 만약 빈칸이 존재하면, 바닥으로 재배치
            if (!countEmpties.isEmpty())
                gravity(field, x, countEmpties);
        }
    }

    /**
     * 바닥으로 뿌요를 재배치하는 함수
     * <p>
     * 1. 빈칸 길이 목록에서 젤 바닥에서 부터 잰 길이를 먼저 받는다
     * 2. 바닥에서부터 처음으로 등장하는 빈칸부터 해당 길이만큼 위의 값을 내린다
     * 3. 해당 길이 바로 다음에 해당 길이와 빈칸 길이 목록의 다음 값을 더한 만큼 위의 값을 내린다
     *
     * @param field        뿌요필드
     * @param x            해당 세로 줄 번호
     * @param countEmpties 빈칸 길이 목록 저장한 자료구조
     */
    private static void gravity(char[][] field, int x, Queue<Integer> countEmpties) {
        // 바닥의 좌표
        int y = 11;
        // 바닥에서부터 첫 빈칸의 좌표 탐색
        while (field[y][x] != '.')
            y--;

        // 내릴 뿌요의 위치(현재 위치에서 뿌요까지의 길이)
        int now = 0;

        while (!countEmpties.isEmpty()) {
            // 길이 목록에서 해당 값을 받아와 누적(이전에 내린만큼 같이 내려야 하기 때문)
            now += countEmpties.poll();

            while (true) {
                // 범위를 벗어나면 종료
                if (y < now)
                    break;

                // 내릴 좌표의 값이 빈칸이라면 종료(끝났기 떄문)
                if (field[y - now][x] == '.')
                    break;

                // 목표 좌표에서 뿌요를 내린 후 제거함
                int target = y - now;
                field[y--][x] = field[target][x];
                field[target][x] = '.';
            }
        }
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 1. 꽃혀 있는 칸까지 보면서 있는지 확인 (함수화)
// 2. 없다면 남은 칸에 연결
// 3. 남은 칸이 없다면
//    콘센트에 있는 숫자를 돌면서
//    가장 멀리 있거나, 없는 콘센트 사용 하면서 정답에 +1
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Deque<Integer>[] waiting = new ArrayDeque[101];
        for (int i = 1; i <= 100; i++)
            waiting[i] = new ArrayDeque<>();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] multitap = new int[n];
        int[] order = new int[k];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++)
            waiting[order[i] = Integer.parseInt(st.nextToken())].addLast(i);

        int i = 0;
        int use = 0;
        for (; i < k; i++) {
            if (use == n)
                break;

            int now = order[i];
            if (isUse(now, use, multitap)) {
                waiting[now].removeFirst();
                continue;
            }

            multitap[use++] = now;
            waiting[now].removeFirst();
        }

        int out = 0;
        for (; i < k; i++) {
            int now = order[i];
            if (isUse(now, use, multitap)) {
                waiting[now].removeFirst();
                continue;
            }

            int position = findTarget(n, multitap, waiting);

            waiting[now].removeFirst();
            multitap[position] = now;
            out++;
        }

        System.out.println(out);
    }

    private static int findTarget(int n, int[] multitap, Deque<Integer>[] waiting) {
        int target = 0;
        int far = 0;
        for (int i = 0; i < n; i++) {
            int now = multitap[i];
            if (waiting[now].isEmpty())
                return i;

            int wait = waiting[now].peek();

            if (wait <= far)
                continue;

            target = i;
            far = wait;
        }

        return target;
    }

    private static boolean isUse(int target, int use, int[] multitap) {
        for (int i = 0; i < use; i++)
            if (multitap[i] == target)
                return true;
        return false;
    }
}

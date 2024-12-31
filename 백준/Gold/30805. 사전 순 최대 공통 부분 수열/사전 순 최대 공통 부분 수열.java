import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static class Info implements Comparable<Info> {
        int index;
        int value;

        public Info(int index, int value) {
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Info o) {
            if (value != o.value)
                return o.value - value;
            return index - o.index;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        Info[] a = new Info[n];
        for (int i = 0; i < n; i++) {
            a[i] = new Info(i, Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(a);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        Info[] b = new Info[m];
        for (int i = 0; i < m; i++) {
            b[i] = new Info(i, Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(b);

        System.out.println(process(n, a, m, b));
    }

    private static String process(int n, Info[] a, int m, Info[] b) {
        Stack<Integer> stack = new Stack<>();

        int i = 0;
        int j = 0;

        int lastI = i;
        int lastJ = j;

        while (true) {
            if (i == n || j == m) {
                break;
            }

            if (a[i].index < lastI) {
                i++;
                continue;
            } else if (b[j].index < lastJ) {
                j++;
                continue;
            }

            if (a[i].value == b[j].value) {
                stack.add(a[i].value);
                lastI = a[i].index;
                lastJ = b[j].index;
                i++;
                j++;
            } else if (a[i].value > b[j].value) {
                i++;
            } else {
                j++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(stack.size());
        if (!stack.isEmpty()) {
            sb.append("\n");
            for (int now : stack) {
                sb.append(now).append(" ");
            }
        }

        return sb.toString();
    }
}
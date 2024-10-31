import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Container {
        int priority;
        int weight;

        public Container(int priority, int weight) {
            this.priority = priority;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Queue<Container> containers = new ArrayDeque<>();
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int priority = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            containers.add(new Container(priority, weight));
            pq.add(priority);
        }

        System.out.println(process(containers, pq));
    }

    private static int process(Queue<Container> containers, Queue<Integer> pq) {
        List<Integer> nowWeightInfo = new ArrayList<>();
        int nowPriority = pq.poll();

        int price = 0;

        while (true) {
            Container now = containers.poll();

            // 다음 컨테이너가 같은 우선순위
            if (now.priority == nowPriority) {
                int target = now.weight * 2;
                for (int nowWeight : nowWeightInfo) {
                    if (nowWeight >= target)
                        break;
                    price += nowWeight;
                }

                price += now.weight;

                if (pq.isEmpty())
                    break;
                int nextPriority = pq.poll();
                if (nowPriority != nextPriority) {
                    nowPriority = nextPriority;
                    nowWeightInfo = new ArrayList<>();
                } else {
                    nowWeightInfo.add(target);
                    Collections.sort(nowWeightInfo);
                }
            }
            // 다음 컨테이너가 다른 우선순위
            else {
                price += now.weight;
                containers.add(now);
            }
        }

        return price;
    }
}
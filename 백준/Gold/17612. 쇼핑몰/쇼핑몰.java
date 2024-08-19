import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Counter implements Comparable<Counter> {
        int number;
        Customer customer;

        public Counter(int number, Customer customer) {
            this.number = number;
            this.customer = customer;
        }

        @Override
        public int compareTo(Counter o) {
            if (customer.w != o.customer.w)
                return Integer.compare(customer.w, o.customer.w);
            return Integer.compare(o.number, number);
        }
    }

    static class Customer {
        int id;
        int w;

        public Customer(int id, int w) {
            this.id = id;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> empty = new PriorityQueue<>();
        for (int i = 1; i <= k; i++)
            empty.add(i);

        Queue<Customer> waiting = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            waiting.add(new Customer(id, w));
        }

        System.out.println(process(empty, waiting));
    }

    private static long process(Queue<Integer> empty, Queue<Customer> waiting) {
        Queue<Counter> paying = new PriorityQueue<>();

        long answer = 0;

        int time = 0;
        int count = 0;

        while (!waiting.isEmpty() || !paying.isEmpty()) {
            // empty 가 있다면 부여
            while (!empty.isEmpty() && !waiting.isEmpty()) {
                Customer now = waiting.poll();
                now.w += time;
                paying.add(new Counter(empty.poll(), now));
            }

            // 현재 시각과 계산중인 사람의 시간이 일치하지 않으면 시간 흐름
            if (!paying.isEmpty())
                time = paying.peek().customer.w;

            // 흐른 시간과 일치하는 사람 제거 후
            // count 증가 answer 에 추가
            while (!paying.isEmpty() && paying.peek().customer.w == time) {
                Counter now = paying.poll();

                answer += (long) ++count * now.customer.id;
                empty.add(now.number);
            }
        }

        return answer;
    }
}
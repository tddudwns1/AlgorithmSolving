import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Station implements Comparable<Station>{
        int fuel;
        int spot;

        public Station(int fuel, int spot) {
            this.fuel = fuel;
            this.spot = spot;
        }

        @Override
        public int compareTo(Station o) {
            return Integer.compare(spot, o.spot);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        Queue<Station> stations = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int spot = Integer.parseInt(st.nextToken());
            int fuel = Integer.parseInt(st.nextToken());

            stations.add(new Station(fuel, spot));
        }

        st = new StringTokenizer(br.readLine());
        int distance = Integer.parseInt(st.nextToken());
        int fuel = Integer.parseInt(st.nextToken());

        System.out.println(process(stations, distance, fuel));
    }

    private static int process(Queue<Station> stations, int distance, int fuel) {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int position = fuel;
        int count = 0;

        while (true) {
            if (position >= distance)
                return count;

            while (!stations.isEmpty())
                if (stations.peek().spot <= position)
                    pq.add(stations.poll().fuel);
                else
                    break;

            if (pq.isEmpty())
                return -1;

            position += pq.poll();
            count++;
        }
    }
}
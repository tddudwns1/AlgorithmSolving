import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int work : works) {
            pq.offer(work);
        }
        
        for (int i = 0; i < n; i++) {
            int max = pq.poll();
            if (max <= 0)
                break;
            pq.add(max - 1);
        }
        
        long sum = 0;
        while (!pq.isEmpty()) {
            sum += Math.pow(pq.poll(), 2);
        }
        
        return sum;
    }
}
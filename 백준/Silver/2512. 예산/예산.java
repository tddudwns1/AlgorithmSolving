import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long[] b = new long[n];
        long max = 0;
        for(int i = 0; i<n; i++){
            b[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, b[i]);
        }

        long m = Integer.parseInt(br.readLine());

        long start = 1;
        long end = max;
        long sum = 0;
        while(true) {
            if(start > end) break;

            long mid = (start + end) / 2;

            sum = total(mid, n, b);

            if(sum <= m){
                start = mid+1;
            }
            else {
                end = mid-1;
            }
        }

        System.out.println(start-1);

    }

    public static long total(long mid, int n, long[] b) {
        int sum = 0;
        for(int i = 0; i<n; i++){
            if(b[i] <= mid) sum += b[i];
            else sum += mid;
        }

        return sum;
    }
}

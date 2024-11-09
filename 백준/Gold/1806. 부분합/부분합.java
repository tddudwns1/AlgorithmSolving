import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int[] sequence = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            sequence[i] = Integer.parseInt(st.nextToken());

        int answer = 0;
        int shortest = Integer.MAX_VALUE;
        int len = 0;
        int sum = 0;

        int left = 0;
        int right = 0;
        while(true){
            if(sum < s){
                if(right == n)
                    break;
                sum += sequence[right++];
                len++;
            }else{
                answer = shortest = Integer.min(shortest, len);
                if(answer == 1)
                    break;
                sum -= sequence[left++];
                len--;
            }
        }

        System.out.println(answer);
    }
}

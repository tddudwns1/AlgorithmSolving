import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        while(st.hasMoreTokens()) {
            int now = Integer.parseInt(st.nextToken());
            if (now == -999)
                break;

            max = Math.max(max, now);
            min = Math.min(min, now);
        }

        System.out.println(max + " " + min);
    }
}
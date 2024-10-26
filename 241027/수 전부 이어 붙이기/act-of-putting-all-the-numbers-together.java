import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            sb.append(st.nextToken());
        }
        
        StringBuilder sb2 = new StringBuilder();

        int i = 0;
        for (; i + 5 < sb.length(); i += 5) {
            sb2.append(sb.substring(i, i + 5)).append("\n");
        }
        sb2.append(sb.substring(i, sb.length()));

        System.out.println(sb2);
    }
}
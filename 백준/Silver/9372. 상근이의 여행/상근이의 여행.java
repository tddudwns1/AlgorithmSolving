import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            sb.append(Integer.parseInt(st.nextToken()) - 1).append("\n");
            
            if (--T == 0)
                break;
            
            int M = Integer.parseInt(st.nextToken());

            while(M-- > 0)
                br.readLine();
        }

        System.out.print(sb);
    }
}
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = -100;

        for (int i = 0; i < 3; i++)
            answer = Math.max(answer, Integer.parseInt(st.nextToken()));

        System.out.println(answer);
    }
}
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        long answer = 1;
        
        for (int i = 2; i <= N; i++)
            answer *= i;
        
        System.out.println(answer);
    }
}
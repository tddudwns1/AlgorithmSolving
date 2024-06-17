import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();
        int C = Integer.parseInt(br.readLine());
        
        System.out.println(Integer.parseInt(A) + Integer.parseInt(B) - C);
        System.out.println(Integer.parseInt(A + B) - C);
    }
}
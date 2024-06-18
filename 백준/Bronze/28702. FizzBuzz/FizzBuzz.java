import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        for (int i = 3; i > 0; i--) {
            String now = br.readLine();
            
            if (now.contains("zz"))
                continue;
            
            int candidate = Integer.parseInt(now) + i;
            
            if (candidate % 3 == 0)
                sb.append("Fizz");
            if (candidate % 5 == 0)
                sb.append("Buzz");
            if (sb.length() == 0)
                sb.append(candidate);
            break;
        }
        
        System.out.println(sb);
    }
}
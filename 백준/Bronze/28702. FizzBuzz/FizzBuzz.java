import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";
        
        for (int i = 3; i > 0; i--) {
            String now = br.readLine();
            
            if (now.contains("zz"))
                continue;
            
            int candidate = Integer.parseInt(now) + i;
            
            if (candidate % 3 == 0)
                answer += "Fizz";
            if (candidate % 5 == 0)
                answer += "Buzz";
            if (answer.isEmpty())
                answer = String.valueOf(candidate);
            break;
        }
        
        System.out.println(answer);
    }
}
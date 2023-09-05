import java.io.*;

public class Main{

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String s = br.readLine();
        
        sb.append("I");
        for (int i=0; i<n; i++) {
            sb.append("OI");
        }
        String p = sb.toString();
        
        int end = 2 * n + 1;
        sb = new StringBuilder();
        sb.append(s.substring(0, end));
//        for (int i=0; i<end; i++) {
//            sb.append(s[i]);
//        }
        
        int answer = 0;
        if (sb.toString().equals(p)) {
            answer++;
        }
        
        for (int i=end; i<m; i++) {
            sb.deleteCharAt(0);
            sb.append(s.charAt(i));
            
            if (sb.toString().equals(p)) {
                answer++;
            }
        }
        
        System.out.println(answer);
    }

}
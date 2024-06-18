import java.io.*;
import java.util.*;

class Main {
    static class Register {
        int value;
        String command;
        
        public Register(int value, String command) {
            this.value = value;
            this.command = command;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            sb.append(process(A, B)).append("\n");
        }
        
        System.out.println(sb);
    }
    
    private static String process(int start, int target) {
        Queue<Register> q = new ArrayDeque<>();
        q.add(new Register(start, ""));
        boolean[] checked = new boolean[10000];
        
        while(true) {
            Register now = q.poll();
            
            if(now.value == target)
                return now.command;
            
            Register afterD = d(now);
            if(!checked[afterD.value]){
                q.add(afterD);
                checked[afterD.value] = true;
            }
            
            Register afterS = s(now);
            if(!checked[afterS.value]){
                q.add(afterS);
                checked[afterS.value] = true;
            }
            
            Register afterL = l(now);
            if(!checked[afterL.value]){
                q.add(afterL);
                checked[afterL.value] = true;
            }
            
            Register afterR = r(now);
            if(!checked[afterR.value]){
                q.add(afterR);
                checked[afterR.value] = true;
            }
        }
    }
    
    private static Register d(Register now) {
        int value = now.value;
        String command = now.command;
        
        value = 2 * value % 10000;
        command += "D";
        
        return new Register(value, command);
    }
    
    private static Register s(Register now) {
        int value = now.value;
        String command = now.command;
        
        if(--value == -1) value = 9999;
        command += "S";
        
        return new Register(value, command);
    }
    
    private static Register l(Register now) {
        int value = now.value;
        String command = now.command;
        
        value = value % 1000 * 10 + value / 1000;
        command += "L";
        
        return new Register(value, command);
    }
    
    private static Register r(Register now) {
        int value = now.value;
        String command = now.command;
        
        value = value % 10 * 1000 + value / 10;
        command += "R";
        
        return new Register(value, command);
    }
}
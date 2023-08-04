import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
        	try {
                st = new StringTokenizer(br.readLine());
			} catch (Exception e) {
				break;
			}
        
         if(!st.hasMoreElements())
            break;
        
        String test_case = st.nextToken();
        List<Integer> s = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 8; i++)
                s.add(Integer.parseInt(st.nextToken()));
        int cnt = 1;
        while((s.get(0) - cnt) > 0) {
            int temp = s.get(0) - cnt;
            s.remove(0);
            s.add(temp);
            cnt++;
            if(cnt > 5)
                cnt = 1;
        }
        
        s.remove(0);
        s.add(0);
        
        System.out.print("#" + test_case + " ");
        for(Integer i : s)
            System.out.print(i + " ");
        System.out.println();

    }
}
}


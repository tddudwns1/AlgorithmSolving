import java.io.*;
import java.util.*;

class Solution {
    static int row, col, answer;
    static List<Integer> isChecked;
    
    public int solution(String[][] relation) {
        answer = 0;
        
        row = relation.length;
        col = relation[0].length;
        
        isChecked = new ArrayList<>();
        
        for (int i = 1; i <= col; i++) {
            process(0, 0, i, relation);
        }
        
        return answer;
    }
    
    public void process(int index, int nowChecked, int depth, String[][] relation) {
        if (Integer.bitCount(nowChecked) == depth) {
            Set<String> set = new HashSet<>();
            Stack<Integer> stack = new Stack<>();
            int length = Integer.SIZE - Integer.numberOfLeadingZeros(nowChecked);
            for (int i = 0; i < length; i++)
                if ((nowChecked & (1 << i)) != 0)
                    stack.add(i);
            
            for (int i = 0; i < row; i++) {
                StringBuilder sb = new StringBuilder();
                
                for (int now : stack) {
                    sb.append(relation[i][now]).append(",");
                }
                
                set.add(sb.toString());
            }
            
            if (set.size() < row)
                return;
                
            for (int i = 0; i < isChecked.size(); i++)
                if(isChecked.get(i) == (nowChecked & isChecked.get(i)))
                    return;
                
            isChecked.add(nowChecked);
            
            answer++;
            nowChecked = 0;
            return;
        }
        
        portal: for (int i = index; i < col; i++) {
            for (int j = 0; j < isChecked.size(); j++)
                if((1 << i) == (i & isChecked.get(j)))
                    continue portal;
            
            nowChecked = nowChecked | 1 << i;
            process(i + 1, nowChecked, depth, relation);
            nowChecked = nowChecked & ~(1 << i);
        }
    }
}
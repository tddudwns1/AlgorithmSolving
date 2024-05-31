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
    
    private void process(int index, int nowChecked, int depth, String[][] relation) {
        if (Integer.bitCount(nowChecked) == depth) {
            if (!isUnique(nowChecked, relation)) return;
            if (!isMinimal(nowChecked)) return;
            
            isChecked.add(nowChecked);
            answer++;
            return;
        }
        
        for (int i = index; i < col; i++) {
            if (isPartOfExistingCombination(i)) continue;
            
            nowChecked |= 1 << i;
            process(i + 1, nowChecked, depth, relation);
            nowChecked &= ~(1 << i);
        }
    }
    
    private boolean isUnique(int nowChecked, String[][] relation) {
        Set<String> set = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        int length = Integer.SIZE - Integer.numberOfLeadingZeros(nowChecked);
        
        for (int i = 0; i < length; i++) {
            if ((nowChecked & (1 << i)) != 0) {
                stack.add(i);
            }
        }
        
        for (int i = 0; i < row; i++) {
            StringBuilder sb = new StringBuilder();
            for (int now : stack) {
                sb.append(relation[i][now]).append(",");
            }
            set.add(sb.toString());
        }
        
        return set.size() == row;
    }
    
    private boolean isMinimal(int nowChecked) {
        for (int checked : isChecked) {
            if ((nowChecked & checked) == checked) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isPartOfExistingCombination(int i) {
        for (int checked : isChecked) {
            if ((1 << i) == (i & checked)) {
                return true;
            }
        }
        return false;
    }
}

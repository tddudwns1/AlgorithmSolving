import java.util.*;
import java.lang.StringBuilder;

class Solution {
    public String[] solution(String[] record) {
        int len = record.length;
        Map<String, String> user = new HashMap<>();
        ArrayList<String[]> newRecord = new ArrayList<>();
        for(int i = 0; i < len; i++){
            String[] ans = record[i].split(" ");
            if(ans[0].equals("Enter")){
                user.put(ans[1], ans[2]);
                newRecord.add(new String[]{"e", ans[1]});
            } else if(ans[0].equals("Leave")){
                newRecord.add(new String[]{"o", ans[1]});
            } else if(ans[0].equals("Change")){
                user.put(ans[1], ans[2]);
            }
        }
        len = newRecord.size();
        String[] answer = new String[len];
        
        for(int i = 0; i < len; i++){
            if(newRecord.get(i)[0].equals("e")){
                answer[i] = user.get(newRecord.get(i)[1]) + "님이 들어왔습니다.";
            }else if(newRecord.get(i)[0].equals("o")){
                answer[i] = user.get(newRecord.get(i)[1]) + "님이 나갔습니다.";
            }
        }
        
        
        return answer;
    }
}
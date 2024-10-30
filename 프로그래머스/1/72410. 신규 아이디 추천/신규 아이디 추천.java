class Solution {
    public String solution(String new_id) {
        String answer = 
            new_id
            .toLowerCase()
            .replaceAll("[^a-z0-9_.-]", "")
            .replaceAll("(\\.)+", ".")
            .replaceAll("^[.]+|[.]+$", "")
            .replaceAll("^$", "a")
            .replaceAll("^(.{0,15}).*", "$1")
            .replaceAll("^[.]+|[.]+$", "");
        
        while (answer.length() <= 2) {
            answer += answer.substring(answer.length() - 1);
        }
        
        return answer;
    }
}